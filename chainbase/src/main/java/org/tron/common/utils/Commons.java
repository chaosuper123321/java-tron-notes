package org.tron.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.parameter.CommonParameter;
import org.tron.core.capsule.AccountCapsule;
import org.tron.core.capsule.ExchangeCapsule;
import org.tron.core.exception.BalanceInsufficientException;
import org.tron.core.store.AccountStore;
import org.tron.core.store.AssetIssueStore;
import org.tron.core.store.AssetIssueV2Store;
import org.tron.core.store.DynamicPropertiesStore;
import org.tron.core.store.ExchangeStore;
import org.tron.core.store.ExchangeV2Store;


/**
 * 提供一系列公共工具方法，用于处理地址解码、余额调整、资产交换等功能，以支持区块链平台的基本操作。
 */
@Slf4j(topic = "Commons")
public class Commons {

  public static final int ASSET_ISSUE_COUNT_LIMIT_MAX = 1000;

  //对Base58Check编码的字符串进行解码。
  public static byte[] decode58Check(String input) {
    byte[] decodeCheck = Base58.decode(input);
    if (decodeCheck.length <= 4) {
      return null;
    }
    byte[] decodeData = new byte[decodeCheck.length - 4];
    System.arraycopy(decodeCheck, 0, decodeData, 0, decodeData.length);
    byte[] hash0 = Sha256Hash.hash(CommonParameter.getInstance().isECKeyCryptoEngine(),
        decodeData);
    byte[] hash1 = Sha256Hash.hash(CommonParameter.getInstance().isECKeyCryptoEngine(),
        hash0);
    if (hash1[0] == decodeCheck[decodeData.length] &&
        hash1[1] == decodeCheck[decodeData.length + 1] &&
        hash1[2] == decodeCheck[decodeData.length + 2] &&
        hash1[3] == decodeCheck[decodeData.length + 3]) {
      return decodeData;
    }
    return null;
  }

  //从Base58Check编码的地址中解码出原始地址。
  public static byte[] decodeFromBase58Check(String addressBase58) {
    if (StringUtils.isEmpty(addressBase58)) {
      logger.warn("Warning: Address is empty !!");
      return null;
    }
    byte[] address = decode58Check(addressBase58);
    if (address == null) {
      return null;
    }

    if (!DecodeUtil.addressValid(address)) {
      return null;
    }

    return address;
  }

  //调整账户余额。
  public static void adjustBalance(AccountStore accountStore, byte[] accountAddress, long amount)
      throws BalanceInsufficientException {
    AccountCapsule account = accountStore.getUnchecked(accountAddress);
    adjustBalance(accountStore, account, amount);
  }

  //调整账户余额。
  public static void adjustBalance(AccountStore accountStore, AccountCapsule account, long amount)
      throws BalanceInsufficientException {

    long balance = account.getBalance();
    if (amount == 0) {
      return;
    }

    if (amount < 0 && balance < -amount) {
      throw new BalanceInsufficientException(
          String.format("%s insufficient balance, balance: %d, amount: %d",
              StringUtil.createReadableString(account.createDbKey()), balance, -amount));
    }
    account.setBalance(Math.addExact(balance, amount));
    accountStore.put(account.getAddress().toByteArray(), account);
  }

  //根据系统配置返回适当的交易所存储。
  public static ExchangeStore getExchangeStoreFinal(DynamicPropertiesStore dynamicPropertiesStore,
      ExchangeStore exchangeStore,
      ExchangeV2Store exchangeV2Store) {
    if (dynamicPropertiesStore.getAllowSameTokenName() == 0) {
      return exchangeStore;
    } else {
      return exchangeV2Store;
    }
  }

  //存储交易所Capsule信息。
  public static void putExchangeCapsule(ExchangeCapsule exchangeCapsule,
      DynamicPropertiesStore dynamicPropertiesStore, ExchangeStore exchangeStore,
      ExchangeV2Store exchangeV2Store, AssetIssueStore assetIssueStore) {
    if (dynamicPropertiesStore.getAllowSameTokenName() == 0) {
      exchangeStore.put(exchangeCapsule.createDbKey(), exchangeCapsule);
      ExchangeCapsule exchangeCapsuleV2 = new ExchangeCapsule(exchangeCapsule.getData());
      exchangeCapsuleV2.resetTokenWithID(assetIssueStore, dynamicPropertiesStore);
      exchangeV2Store.put(exchangeCapsuleV2.createDbKey(), exchangeCapsuleV2);
    } else {
      exchangeV2Store.put(exchangeCapsule.createDbKey(), exchangeCapsule);
    }
  }

  //根据系统配置返回适当的资产发行存储。
  public static AssetIssueStore getAssetIssueStoreFinal(
      DynamicPropertiesStore dynamicPropertiesStore,
      AssetIssueStore assetIssueStore, AssetIssueV2Store assetIssueV2Store) {
    if (dynamicPropertiesStore.getAllowSameTokenName() == 0) {
      return assetIssueStore;
    } else {
      return assetIssueV2Store;
    }
  }

  //调整账户中指定资产的余额。
  public static void adjustAssetBalanceV2(AccountCapsule account, String AssetID, long amount,
      AccountStore accountStore, AssetIssueStore assetIssueStore,
      DynamicPropertiesStore dynamicPropertiesStore)
      throws BalanceInsufficientException {
    if (amount < 0) {
      if (!account.reduceAssetAmountV2(AssetID.getBytes(), -amount, dynamicPropertiesStore,
          assetIssueStore)) {
        throw new BalanceInsufficientException(
            String.format("reduceAssetAmount failed! account: %s",
                    StringUtil.encode58Check(account.createDbKey())));
      }
    } else if (amount > 0 &&
        !account.addAssetAmountV2(AssetID.getBytes(), amount, dynamicPropertiesStore,
            assetIssueStore)) {
      throw new BalanceInsufficientException(
          String.format("addAssetAmount failed! account: %s",
                  StringUtil.encode58Check(account.createDbKey())));
    }
    accountStore.put(account.getAddress().toByteArray(), account);
  }

  public static void adjustTotalShieldedPoolValue(long valueBalance,
      DynamicPropertiesStore dynamicPropertiesStore) throws BalanceInsufficientException {
    long totalShieldedPoolValue = Math
        .subtractExact(dynamicPropertiesStore.getTotalShieldedPoolValue(), valueBalance);
    if (totalShieldedPoolValue < 0) {
      throw new BalanceInsufficientException(String.format(
          "total shielded pool value can not below 0, actual: %d", totalShieldedPoolValue));
    }
    dynamicPropertiesStore.saveTotalShieldedPoolValue(totalShieldedPoolValue);
  }

  //调整账户中指定资产的余额。
  public static void adjustAssetBalanceV2(byte[] accountAddress, String AssetID, long amount
      , AccountStore accountStore, AssetIssueStore assetIssueStore,
      DynamicPropertiesStore dynamicPropertiesStore)
      throws BalanceInsufficientException {
    AccountCapsule account = accountStore.getUnchecked(accountAddress);
    adjustAssetBalanceV2(account, AssetID, amount, accountStore, assetIssueStore,
        dynamicPropertiesStore);
  }
}
