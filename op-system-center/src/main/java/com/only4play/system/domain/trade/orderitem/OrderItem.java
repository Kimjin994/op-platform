package com.only4play.system.domain.trade.orderitem;

import com.only4play.codegen.processor.creator.GenCreator;
import com.only4play.codegen.processor.query.GenQuery;
import com.only4play.codegen.processor.updater.GenUpdater;
import com.only4play.codegen.processor.vo.GenVo;
import com.only4play.common.annotation.FieldDesc;
import com.only4play.jpa.support.BaseJpaAggregate;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.only4play.system.domain.trade.orderitem.vo")
@GenCreator(pkgName = "com.only4play.system.domain.trade.orderitem.creator")
@GenUpdater(pkgName = "com.only4play.system.domain.trade.orderitem.updater")
@GenQuery(pkgName = "com.only4play.system.domain.trade.orderitem.query")
@Entity
@Table(name = "order_item")
@Data
public class OrderItem extends BaseJpaAggregate {

  @FieldDesc(name = "订单id")
  private Long orderId;

  @FieldDesc(name = "唯一流水号")
  private Long flowNo;

  @FieldDesc(name = "真实金额")
  private BigDecimal realAmount;

  @FieldDesc(name = "计量数量")
  private BigDecimal itemCount;

  @FieldDesc(name = "skuId")
  private String skuId;

  @FieldDesc(name = "单位")
  private String itemUnit;

  @FieldDesc(name = "商品名称")
  private String goodsName;

  @FieldDesc(name = "费用描述")
  private String feeRemark;

}
