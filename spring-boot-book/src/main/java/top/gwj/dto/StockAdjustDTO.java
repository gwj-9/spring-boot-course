package top.gwj.dto;

import lombok.Data;

@Data
public class StockAdjustDTO {
    private Integer delta; // 库存变化量（正数入库，负数出库）
}