
package com.spp.core.pojo;

import com.spp.common.enums.DateField;
import com.spp.core.enums.CityNameParserEnum;
import com.spp.core.enums.IpCrawlerTypeEnum;
import com.spp.core.enums.IpValueParserEnum;
import com.spp.core.enums.PortValueParserEnum;



/**
 * 爬虫配置
 * @author zhougy
 * @date 2024/05/22
 */
public class Crawler {

    /** 默认 **/
    private String value = "";

    /** 是否开启 **/
    private Boolean enable = true;

    /** 唯一值 **/
    private String key = "";

    /** 名称 **/
    private String name = "";

    /** 地址 **/
    private String baseUrl = "";

    /** 总页数 **/
    private Integer pages = 1;

    /** 页码别称字典json **/
    private String pageNoDict = "";

    /** 爬虫类型 **/
    private IpCrawlerTypeEnum type = IpCrawlerTypeEnum.HTML;

    /** 行解析器（JSON） **/
    private String rowsParser = "";

    /** ip解析器（JSON） **/
    private String ipParser = "";

    /** 端口解析器（JSON） **/
    private String portParser = "";

    /** 城市解析器（JSON） **/
    private String cityParser = "";

    /** 行选择器（HTML） **/
    private String rowsSelector = "";

    /** cell选择器（HTML） **/
    private String cellSelector = "";

    /** 表头下标 **/
    private Integer headRowIndex = 0;

    /** ip下标 **/
    private Integer ipIndex = 0;

    /** ip值策略 **/
    private IpValueParserEnum ipValueParser = IpValueParserEnum.DEFAULT;

    /** 端口下标 **/
    private Integer portIndex = 1;

    /** 端口值策略 **/
    private PortValueParserEnum portValueParser = PortValueParserEnum.DEFAULT;

    /** 城市下标 **/
    private Integer cityIndex = 2;

    /** 城市解析策略 **/
    private CityNameParserEnum cityNameParser;

    /** 过期时间单位 **/
    private DateField expireUnit = DateField.MINUTE;

    /** 过期时间偏移值 **/
    private Integer expireOffset = 10;

    /** 是否上锁 **/
    private boolean lock = false;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPageNoDict() {
        return pageNoDict;
    }

    public void setPageNoDict(String pageNoDict) {
        this.pageNoDict = pageNoDict;
    }

    public IpCrawlerTypeEnum getType() {
        return type;
    }

    public void setType(IpCrawlerTypeEnum type) {
        this.type = type;
    }

    public String getRowsParser() {
        return rowsParser;
    }

    public void setRowsParser(String rowsParser) {
        this.rowsParser = rowsParser;
    }

    public String getIpParser() {
        return ipParser;
    }

    public void setIpParser(String ipParser) {
        this.ipParser = ipParser;
    }

    public String getPortParser() {
        return portParser;
    }

    public void setPortParser(String portParser) {
        this.portParser = portParser;
    }

    public String getCityParser() {
        return cityParser;
    }

    public void setCityParser(String cityParser) {
        this.cityParser = cityParser;
    }

    public String getRowsSelector() {
        return rowsSelector;
    }

    public void setRowsSelector(String rowsSelector) {
        this.rowsSelector = rowsSelector;
    }

    public String getCellSelector() {
        return cellSelector;
    }

    public void setCellSelector(String cellSelector) {
        this.cellSelector = cellSelector;
    }

    public Integer getHeadRowIndex() {
        return headRowIndex;
    }

    public void setHeadRowIndex(Integer headRowIndex) {
        this.headRowIndex = headRowIndex;
    }

    public Integer getIpIndex() {
        return ipIndex;
    }

    public void setIpIndex(Integer ipIndex) {
        this.ipIndex = ipIndex;
    }

    public IpValueParserEnum getIpValueParser() {
        return ipValueParser;
    }

    public void setIpValueParser(IpValueParserEnum ipValueParser) {
        this.ipValueParser = ipValueParser;
    }

    public Integer getPortIndex() {
        return portIndex;
    }

    public void setPortIndex(Integer portIndex) {
        this.portIndex = portIndex;
    }

    public PortValueParserEnum getPortValueParser() {
        return portValueParser;
    }

    public void setPortValueParser(PortValueParserEnum portValueParser) {
        this.portValueParser = portValueParser;
    }

    public Integer getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(Integer cityIndex) {
        this.cityIndex = cityIndex;
    }

    public CityNameParserEnum getCityNameParser() {
        return cityNameParser;
    }

    public void setCityNameParser(CityNameParserEnum cityNameParser) {
        this.cityNameParser = cityNameParser;
    }

    public DateField getExpireUnit() {
        return expireUnit;
    }

    public void setExpireUnit(DateField expireUnit) {
        this.expireUnit = expireUnit;
    }

    public Integer getExpireOffset() {
        return expireOffset;
    }

    public void setExpireOffset(Integer expireOffset) {
        this.expireOffset = expireOffset;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "Crawler{" +
                "value='" + value + '\'' +
                ", enable=" + enable +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", pages=" + pages +
                ", pageNoDict='" + pageNoDict + '\'' +
                ", type=" + type +
                ", rowsParser='" + rowsParser + '\'' +
                ", ipParser='" + ipParser + '\'' +
                ", portParser='" + portParser + '\'' +
                ", cityParser='" + cityParser + '\'' +
                ", rowsSelector='" + rowsSelector + '\'' +
                ", cellSelector='" + cellSelector + '\'' +
                ", headRowIndex=" + headRowIndex +
                ", ipIndex=" + ipIndex +
                ", ipValueParser=" + ipValueParser +
                ", portIndex=" + portIndex +
                ", portValueParser=" + portValueParser +
                ", cityIndex=" + cityIndex +
                ", cityNameParser=" + cityNameParser +
                ", expireUnit=" + expireUnit +
                ", expireOffset=" + expireOffset +
                ", lock=" + lock +
                '}';
    }
}
