package org.tron.common.runtime;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.tron.common.logsfilter.trigger.ContractTrigger;
import org.tron.core.vm.utils.MUtil;
import org.tron.protos.contract.SmartContractOuterClass.SmartContract;
import org.tron.protos.contract.SmartContractOuterClass.SmartContract.ABI.Entry.Param;

/**
 * 封装日志事件，提供对智能合约日志事件的处理和解析功能。
 */
public class LogEventWrapper extends ContractTrigger {

  //存储主题列表
  @Getter
  @Setter
  private List<byte[]> topicList;

  //存储数据
  @Getter
  @Setter
  private byte[] data;

  //存储事件签名
  @Getter
  @Setter
  private String eventSignature;

  //存储ABI条目
  @Getter
  @Setter
  private SmartContract.ABI.Entry abiEntry;

  //初始化对象
  public LogEventWrapper() {
    super();
  }

  //获取完整的事件签名
  public String getEventSignatureFull() {
    if (Objects.isNull(abiEntry)) {
      return "fallback()";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(abiEntry.getName()).append("(");
    StringBuilder sbp = new StringBuilder();
    for (Param param : abiEntry.getInputsList()) {
      if (sbp.length() > 0) {
        sbp.append(",");
      }
      sbp.append(param.getType());
      if (MUtil.isNotNullOrEmpty(param.getName())) {
        sbp.append(" ").append(param.getName());
      }
    }
    sb.append(sbp.toString()).append(")");
    return sb.toString();
  }
}
