package com.only4play.system.domain.asset.objectsku;

import com.only4play.codegen.processor.repository.GenRepository;
import com.only4play.jpa.support.BaseJpaAggregate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@GenRepository(pkgName = "com.only4play.system.domain.asset.objectsku.repository")
@Entity
@Table(name = "sku_attribute_rel")
@Data
public class SkuAttributeRel extends BaseJpaAggregate {

  private Long skuId;

  private Long attributeId;

}
