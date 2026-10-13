package com.only4play.system.domain.asset.asset.request;

import com.only4play.common.model.Request;
import java.util.List;
import lombok.Data;

/**
 * sku 入库
 */
@Data
public class AssetInWarehouseRequest implements Request {

  private Long warehouseId;

  private Long skuId;

  private String name;

  private List<String> uniqueCodes;

  private String operateUser;

  private String batchNo;

}
