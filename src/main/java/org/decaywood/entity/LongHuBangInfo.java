package org.decaywood.entity;

import org.decaywood.utils.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: decaywood
 * @date: 2015/11/27 0:29
 */
public class LongHuBangInfo implements DeepCopy<LongHuBangInfo> {

    private final Stock stock;
    private final Date date;

    private final Set<BizsunitInfo> topBuyList;
    private final Set<BizsunitInfo> topSaleList;

    public static class BizsunitInfo {
        private final String bizsunitcode; //营业部编号
        private final String bizsunitname; //营业部名称
        private final String buyamt; // 买入额度
        private final String saleamt; // 卖出额度
        private final String tradedate; //交易日期 yyyymmdd

        public BizsunitInfo(String bizsunitcode, String bizsunitname, String buyamt, String saleamt, String tradedate) {

            if(StringUtils.nullOrEmpty(bizsunitcode, bizsunitname, buyamt, saleamt, tradedate))
                throw new IllegalArgumentException();

            this.bizsunitcode = bizsunitcode;
            this.bizsunitname = bizsunitname;
            this.buyamt = buyamt;
            this.saleamt = saleamt;
            this.tradedate = tradedate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BizsunitInfo info = (BizsunitInfo) o;

            return bizsunitname.equals(info.bizsunitname);

        }

        @Override
        public int hashCode() {
            return bizsunitname.hashCode();
        }
    }

    public LongHuBangInfo(Stock stock, Date date, Set<BizsunitInfo> topBuyList, Set<BizsunitInfo> topSaleList) {
        this.stock = stock;
        this.date = date;
        this.topBuyList = topBuyList;
        this.topSaleList = topSaleList;
    }

    public Stock getStock() {
        return stock;
    }

    public Date getDate() {
        return date;
    }

    //龙虎榜买入是否有该营业部出现
    public boolean bizsunitInBuyList(String name) {
        return topBuyList.contains(new BizsunitInfo("xx", name, "xx", "xx", "xx"));
    }

    //龙虎榜卖出是否有该营业部出现
    public boolean bizsunitInSaleList(String name) {
        return topSaleList.contains(new BizsunitInfo("xx", name, "xx", "xx", "xx"));
    }

    public Set<BizsunitInfo> getTopBuyList() {
        return topBuyList;
    }

    public Set<BizsunitInfo> getTopSaleList() {
        return topSaleList;
    }

    @Override
    public LongHuBangInfo copy() {
        return new LongHuBangInfo(stock.copy(), date, new HashSet<>(topBuyList), new HashSet<>(topBuyList));
    }
}
