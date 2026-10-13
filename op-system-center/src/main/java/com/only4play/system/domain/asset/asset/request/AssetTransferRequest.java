package com.only4play.system.domain.asset.asset.request;

import com.only4play.common.annotation.FieldDesc;
import com.only4play.common.model.Request;
import java.util.List;
import lombok.Data;

/**
 * 仓库调拨请求体
 */
@Data
public class AssetTransferRequest implements Request {

  @FieldDesc(name = "skuId")
  private Long skuId;

  @FieldDesc(name = "操作用户")
  private String operateUser;

  @FieldDesc(name = "转入仓库id")
  private Long transferInHouseId;

  @FieldDesc(name = "转出仓库id")
  private Long transferOutHouseId;

  @FieldDesc(name = "唯一编码")
  private List<String> uniqueCodes;

  @FieldDesc(name = "批次号")
  private String batchNo;

}
