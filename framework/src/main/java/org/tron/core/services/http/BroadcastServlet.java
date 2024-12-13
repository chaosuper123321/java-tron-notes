package org.tron.core.services.http;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.ByteArray;
import org.tron.core.Wallet;
import org.tron.core.capsule.TransactionCapsule;
import org.tron.protos.Protocol.Transaction;


@Component
@Slf4j(topic = "API")
public class BroadcastServlet extends RateLimiterServlet {

  @Autowired
  private Wallet wallet;

  /**
   * 主要功能是处理HTTP POST请求，
   *    解析请求参数，
   *    创建交易，
   *    广播交易到网络，
   *    将交易结果以JSON格式返回给客户端
   * @param request
   * @param response
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      PostParams params = PostParams.getPostParams(request);
      Transaction transaction = Util.packTransaction(params.getParams(), params.isVisible());
      TransactionCapsule transactionCapsule = new TransactionCapsule(transaction);
      String transactionID = ByteArray
          .toHexString(transactionCapsule.getTransactionId().getBytes());
      GrpcAPI.Return result = wallet.broadcastTransaction(transaction);
      JSONObject res = JSONObject.parseObject(JsonFormat.printToString(result, params.isVisible()));
      res.put("txid", transactionID);
      response.getWriter().println(res.toJSONString());
    } catch (Exception e) {
      Util.processError(e, response);
    }
  }
}
