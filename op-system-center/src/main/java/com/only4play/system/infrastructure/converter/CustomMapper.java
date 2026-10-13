package com.only4play.system.infrastructure.converter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.only4play.order.commons.pay.PayType;
import com.only4play.system.domain.asset.assetrecord.InOutBizType;
import com.only4play.system.domain.asset.assetrecord.InOutType;
import com.only4play.system.domain.invoice.invoice.InvoiceKind;
import com.only4play.system.domain.invoice.invoice.TitleType;
import com.only4play.system.domain.invoice.orderreceipt.ReceiptStatus;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceStyle;
import com.only4play.system.domain.invoice.orderreceipt.domainservice.model.InvoiceType;
import com.only4play.system.domain.asset.objectsku.SkuType;
import com.only4play.system.domain.message.messagerecord.MsgTypeEnum;
import com.only4play.system.domain.message.messagerecord.NoticeType;
import com.only4play.system.domain.message.messagetemplate.TemplateType;
import com.only4play.system.domain.template.selectdict.DictType;
import com.only4play.system.domain.template.templateitem.InputType;
import com.only4play.system.domain.template.templateitem.ValueType;
import com.only4play.system.domain.trade.order.OrderState;
import com.only4play.system.domain.trade.order.OrderType;
import com.only4play.system.domain.trade.orderlifecycle.OrderOperateType;
import com.only4play.system.domain.user.AccountType;
import java.util.Map;
import java.util.Objects;

/**
 * 枚举自定义转化 在实体类中的枚举都要进行配置
 */
public class CustomMapper {

  public Integer type2Int(InputType type) {
    return type.getCode();
  }

  public InputType int2Type(Integer code) {
    return InputType.of(code).orElse(InputType.TEXT);
  }

  public Integer dictType2Int(DictType dictType) {
    return dictType.getCode();
  }

  public DictType int2DictType(Integer code) {
    return DictType.of(code).orElse(DictType.SELECT_LIST);
  }

  public Integer skuType2Int(SkuType skuType) {
    return skuType.getCode();
  }

  public SkuType int2SkuType(Integer code) {
    return SkuType.of(code).orElse(SkuType.SINGLE);
  }

  public Integer orderType2Int(OrderType orderType) {
    return orderType.getCode();
  }

  public OrderType int2OrderType(Integer code) {
    return OrderType.of(code).orElse(OrderType.CHARGE);
  }

  public Integer accountType2Int(AccountType accountType) {
    return accountType.getCode();
  }

  public AccountType int2AccountType(Integer code) {
    return AccountType.of(code).orElse(AccountType.PERSONAL);
  }


  public Integer opType2Int(OrderOperateType type) {
    return type.getCode();
  }

  public OrderOperateType int2OpType(Integer code) {
    return OrderOperateType.of(code).orElse(OrderOperateType.AUTH_SUCCESS);
  }

  public Integer status2OrderState(OrderState state) {
    return state.getCode();
  }

  public OrderState int2State(Integer code) {
    return OrderState.of(code).orElse(OrderState.WAIT_PAY);
  }

  public ReceiptStatus int2Status(Integer code) {
    return ReceiptStatus.of(code).orElse(ReceiptStatus.UNMAKING);
  }

  public Integer receiptStatus2Int(ReceiptStatus status) {
    return status.getCode();
  }

  public PayType int2PayType(Integer code) {
    return PayType.of(code).orElse(null);
  }

  public Integer payType2Int(PayType payType) {
    return payType.getCode();
  }

  public InvoiceType int2InvoiceType(Integer code) {
    return InvoiceType.of(code).orElse(null);
  }

  public Integer invoiceType2Int(InvoiceType invoiceType) {
    return invoiceType.getCode();
  }

  public InvoiceStyle int2InvoiceStyle(Integer code) {
    return InvoiceStyle.of(code).orElse(null);
  }

  public Integer invoiceStyle2Int(InvoiceStyle invoiceStyle) {
    return invoiceStyle.getCode();
  }

  public TitleType int2TitleType(Integer code) {
    return TitleType.of(code).orElse(null);
  }

  public Integer titleType2Int(TitleType titleType) {
    return titleType.getCode();
  }

  public InvoiceKind int2InvoiceKind(Integer code){
    return InvoiceKind.of(code).orElse(null);
  }

  public Integer invoiceKind2Int(InvoiceKind invoiceKind){
    return invoiceKind.getCode();
  }

  public InOutBizType int2InOutBizType(Integer code){
    return InOutBizType.of(code).orElse(null);
  }

  public Integer inOutBizType2Int(InOutBizType inOutBizType){
    return inOutBizType.getCode();
  }

  public InOutType int2InOutType(Integer code){
    return InOutType.of(code).orElse(null);
  }

  public Integer inOutType2Int(InOutType inOutType){
    return inOutType.getCode();
  }

  public NoticeType int2NoticeType(Integer code){
    return NoticeType.of(code).orElse(null);
  }

  public Integer noticeType2Int(NoticeType noticeType){
    return noticeType.getCode();
  }

  public MsgTypeEnum int2MsgTypeEnum(Integer code){
    return MsgTypeEnum.of(code).orElse(null);
  }

  public Integer msgTypeEnum2Int(MsgTypeEnum msgTypeEnum){
    return msgTypeEnum.getCode();
  }

  public TemplateType int2TemplateType(Integer code){
    return TemplateType.of(code).orElse(null);
  }

  public Integer templateType2Int(TemplateType templateType){
    return templateType.getCode();
  }

  public Map<String,Object> string2Map(String str){
    JSONObject  jsonObject = JSONObject.parseObject(str);
    return jsonObject;
  }

  public String map2String(Map<String,Object> map){
    return JSON.toJSONString(map);
  }

  public ValueType int2ValueType (Integer code){
      if(Objects.isNull(code)){
          return null;
      }
      return ValueType.of(code).orElse(null);
    }

    public Integer valueType2Int(ValueType valueType){
      return valueType.getCode();
    }
}
