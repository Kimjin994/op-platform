package com.only4play.system.domain.asset.asset.request;

import com.only4play.common.model.Request;
import java.util.List;
import lombok.Data;

@Data
public class AssetOutWarehouseRequest implements Request {

  private Long warehouseId;

  private Long skuId;

  private List<String> uniqueCodes;

  private String operateUser;

  private String batchNo;
}
