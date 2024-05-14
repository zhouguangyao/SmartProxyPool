package com.spp.common.utils;

import java.util.HashMap;


/**
 * @author zhougy
 * @date 2024/05/14
 */
public class CityUtils {
    private static final HashMap<String, String> cityPinyinMap = new HashMap<>();

    static {
        cityPinyinMap.put("Beijing", "北京");
        cityPinyinMap.put("Tianjin", "天津");
        cityPinyinMap.put("Shanghai", "上海");
        cityPinyinMap.put("Chongqing", "重庆");
        cityPinyinMap.put("Yinchuan", "银川");
        cityPinyinMap.put("Shizuishan", "石嘴山");
        cityPinyinMap.put("Wuzhong", "吴忠");
        cityPinyinMap.put("Guyuan", "固原");
        cityPinyinMap.put("Zhongwei", "中卫");
        cityPinyinMap.put("Wulumuqi", "乌鲁木齐");
        cityPinyinMap.put("Kelamayi", "克拉玛依");
        cityPinyinMap.put("Lasa", "拉萨");
        cityPinyinMap.put("Huhehaote", "呼和浩特");
        cityPinyinMap.put("Baotou", "包头");
        cityPinyinMap.put("Wuhai", "乌海");
        cityPinyinMap.put("Chifeng", "赤峰");
        cityPinyinMap.put("Tongliao", "通辽");
        cityPinyinMap.put("Eerduosi", "鄂尔多斯");
        cityPinyinMap.put("Hulunbeier", "呼伦贝尔");
        cityPinyinMap.put("Bayannaoer", "巴彦淖尔");
        cityPinyinMap.put("Wulanchabu", "乌兰察布");
        cityPinyinMap.put("Nanning", "南宁");
        cityPinyinMap.put("Liuzhou", "柳州");
        cityPinyinMap.put("Guilin", "桂林");
        cityPinyinMap.put("Wuzhou", "梧州");
        cityPinyinMap.put("Beihai", "北海");
        cityPinyinMap.put("Chongzuo", "崇左");
        cityPinyinMap.put("Laibin", "来宾");
        cityPinyinMap.put("Hezhou", "贺州");
        cityPinyinMap.put("Yulin", "玉林");
        cityPinyinMap.put("Baise", "百色");
        cityPinyinMap.put("Hechi", "河池");
        cityPinyinMap.put("Qinzhou", "钦州");
        cityPinyinMap.put("Fangchenggang", "防城港");
        cityPinyinMap.put("Guigang", "贵港");
        cityPinyinMap.put("Haerbin", "哈尔滨");
        cityPinyinMap.put("Daqing", "大庆");
        cityPinyinMap.put("Qiqihaer", "齐齐哈尔");
        cityPinyinMap.put("Jiamusi", "佳木斯");
        cityPinyinMap.put("Jixi", "鸡西");
        cityPinyinMap.put("Hegang", "鹤岗");
        cityPinyinMap.put("Shuangyashan", "双鸭山");
        cityPinyinMap.put("Mudanjiang", "牡丹江");
        cityPinyinMap.put("Yichun", "伊春");
        cityPinyinMap.put("Qitaihe", "七台河");
        cityPinyinMap.put("Heihe", "黑河");
        cityPinyinMap.put("Suihua", "绥化");
        cityPinyinMap.put("Changchun", "长春");
        cityPinyinMap.put("Jilin", "吉林");
        cityPinyinMap.put("Siping", "四平");
        cityPinyinMap.put("Liaoyuan", "辽源");
        cityPinyinMap.put("Tonghua", "通化");
        cityPinyinMap.put("Baishan", "白山");
        cityPinyinMap.put("Songyuan", "松原");
        cityPinyinMap.put("Baicheng", "白城");
        cityPinyinMap.put("Shenyang", "沈阳");
        cityPinyinMap.put("Dalian", "大连");
        cityPinyinMap.put("Anshan", "鞍山");
        cityPinyinMap.put("Fushun", "抚顺");
        cityPinyinMap.put("Benxi", "本溪");
        cityPinyinMap.put("Dandong", "丹东");
        cityPinyinMap.put("Jinzhou", "锦州");
        cityPinyinMap.put("Yingkou", "营口");
        cityPinyinMap.put("Fuxin", "阜新");
        cityPinyinMap.put("Liaoyang", "辽阳");
        cityPinyinMap.put("Panjin", "盘锦");
        cityPinyinMap.put("Tieling", "铁岭");
        cityPinyinMap.put("Chaoyang", "朝阳");
        cityPinyinMap.put("Huludao", "葫芦岛");
        cityPinyinMap.put("Shijiazhuang", "石家庄");
        cityPinyinMap.put("Tangshan", "唐山");
        cityPinyinMap.put("Handan", "邯郸");
        cityPinyinMap.put("Qinghuangdao", "秦皇岛");
        cityPinyinMap.put("Baoding", "保定");
        cityPinyinMap.put("Zhangjiakou", "张家口");
        cityPinyinMap.put("Chengde", "承德");
        cityPinyinMap.put("Langfang", "廊坊");
        cityPinyinMap.put("Cangzhou", "沧州");
        cityPinyinMap.put("Hengshui", "衡水");
        cityPinyinMap.put("Xingtai", "邢台");
        cityPinyinMap.put("Jinan", "济南");
        cityPinyinMap.put("Qingdao", "青岛");
        cityPinyinMap.put("Zibo", "淄博");
        cityPinyinMap.put("Zaozhuang", "枣庄");
        cityPinyinMap.put("Dongying", "东营");
        cityPinyinMap.put("Yantai", "烟台");
        cityPinyinMap.put("Weifang", "潍坊");
        cityPinyinMap.put("Jining", "济宁");
        cityPinyinMap.put("Taian", "泰安");
        cityPinyinMap.put("Weihai", "威海");
        cityPinyinMap.put("Rizhao", "日照");
        cityPinyinMap.put("Laiwu", "莱芜");
        cityPinyinMap.put("Linyi", "临沂");
        cityPinyinMap.put("Dezhou", "德州");
        cityPinyinMap.put("Liaocheng", "聊城");
        cityPinyinMap.put("Heze", "菏泽");
        cityPinyinMap.put("Binzhou", "滨州");
        cityPinyinMap.put("Nanjing", "南京");
        cityPinyinMap.put("Zhenjiang", "镇江");
        cityPinyinMap.put("Changzhou", "常州");
        cityPinyinMap.put("Wuxi", "无锡");
        cityPinyinMap.put("Suzhou", "苏州");
        cityPinyinMap.put("Xuzhou", "徐州");
        cityPinyinMap.put("Lianyungang", "连云港");
        cityPinyinMap.put("Huaian", "淮安");
        cityPinyinMap.put("Yancheng", "盐城");
        cityPinyinMap.put("Yangzhou", "扬州");
        cityPinyinMap.put("Taizhou", "泰州");
        cityPinyinMap.put("Nantong", "南通");
        cityPinyinMap.put("Suqian", "宿迁");
        cityPinyinMap.put("Hefei", "合肥");
        cityPinyinMap.put("Bengbu", "蚌埠");
        cityPinyinMap.put("Wuhu", "芜湖");
        cityPinyinMap.put("Huainan", "淮南");
        cityPinyinMap.put("Bozhou", "亳州");
        cityPinyinMap.put("Fuyang", "阜阳");
        cityPinyinMap.put("Huaibei", "淮北");
        cityPinyinMap.put("Suzhou", "宿州");
        cityPinyinMap.put("Chuzhou", "滁州");
        cityPinyinMap.put("Anqing", "安庆");
        cityPinyinMap.put("Chaohu", "巢湖");
        cityPinyinMap.put("Maanshan", "马鞍山");
        cityPinyinMap.put("Xuancheng", "宣城");
        cityPinyinMap.put("Huangshan", "黄山");
        cityPinyinMap.put("Chizhou", "池州");
        cityPinyinMap.put("Tongling", "铜陵");
        cityPinyinMap.put("Hangzhou", "杭州");
        cityPinyinMap.put("Jiaxing", "嘉兴");
        cityPinyinMap.put("Huzhou", "湖州");
        cityPinyinMap.put("Ningbo", "宁波");
        cityPinyinMap.put("Jinhua", "金华");
        cityPinyinMap.put("Wenzhou", "温州");
        cityPinyinMap.put("Lishui", "丽水");
        cityPinyinMap.put("Shaoxing", "绍兴");
        cityPinyinMap.put("Quzhou", "衢州");
        cityPinyinMap.put("Zhoushan", "舟山");
        cityPinyinMap.put("Taizhou", "台州");
        cityPinyinMap.put("Fuzhou", "福州");
        cityPinyinMap.put("Xiamen", "厦门");
        cityPinyinMap.put("Quanzhou", "泉州");
        cityPinyinMap.put("Sanming", "三明");
        cityPinyinMap.put("Nanping", "南平");
        cityPinyinMap.put("Zhangzhou", "漳州");
        cityPinyinMap.put("Putian", "莆田");
        cityPinyinMap.put("Ningde", "宁德");
        cityPinyinMap.put("Longyan", "龙岩");
        cityPinyinMap.put("Guangzhou", "广州");
        cityPinyinMap.put("Shenzhen", "深圳");
        cityPinyinMap.put("Shantou", "汕头");
        cityPinyinMap.put("Huizhou", "惠州");
        cityPinyinMap.put("Zhuhai", "珠海");
        cityPinyinMap.put("Jieyang", "揭阳");
        cityPinyinMap.put("Foshan", "佛山");
        cityPinyinMap.put("Heyuan", "河源");
        cityPinyinMap.put("Yangjiang", "阳江");
        cityPinyinMap.put("Maoming", "茂名");
        cityPinyinMap.put("Zhanjiang", "湛江");
        cityPinyinMap.put("Meizhou", "梅州");
        cityPinyinMap.put("Zhaoqing", "肇庆");
        cityPinyinMap.put("Shaoguan", "韶关");
        cityPinyinMap.put("Chaozhou", "潮州");
        cityPinyinMap.put("Dongguan", "东莞");
        cityPinyinMap.put("Zhongshan", "中山");
        cityPinyinMap.put("Qingyuan", "清远");
        cityPinyinMap.put("Jiangmen", "江门");
        cityPinyinMap.put("Shanwei", "汕尾");
        cityPinyinMap.put("Yunfu", "云浮");
        cityPinyinMap.put("Haikou", "海口");
        cityPinyinMap.put("Sanya", "三亚");
        cityPinyinMap.put("Kunming", "昆明");
        cityPinyinMap.put("Qujing", "曲靖");
        cityPinyinMap.put("Yuxi", "玉溪");
        cityPinyinMap.put("Baoshan", "保山");
        cityPinyinMap.put("Zhaotong", "昭通");
        cityPinyinMap.put("Lijiang", "丽江");
        cityPinyinMap.put("Puer", "普洱");
        cityPinyinMap.put("Lincang", "临沧");
        cityPinyinMap.put("Guiyang", "贵阳");
        cityPinyinMap.put("Liupanshui", "六盘水");
        cityPinyinMap.put("Zunyi", "遵义");
        cityPinyinMap.put("Anshun", "安顺");
        cityPinyinMap.put("Chengdu", "成都");
        cityPinyinMap.put("Mianyang", "绵阳");
        cityPinyinMap.put("Deyang", "德阳");
        cityPinyinMap.put("Guangyuan", "广元");
        cityPinyinMap.put("Zigong", "自贡");
        cityPinyinMap.put("Panzhihua", "攀枝花");
        cityPinyinMap.put("Leshan", "乐山");
        cityPinyinMap.put("Nanchong", "南充");
        cityPinyinMap.put("Neijiang", "内江");
        cityPinyinMap.put("Suining", "遂宁");
        cityPinyinMap.put("Guangan", "广安");
        cityPinyinMap.put("Luzhou", "泸州");
        cityPinyinMap.put("Dazhou", "达州");
        cityPinyinMap.put("Meishan", "眉山");
        cityPinyinMap.put("Yibin", "宜宾");
        cityPinyinMap.put("Yaan", "雅安");
        cityPinyinMap.put("Ziyang", "资阳");
        cityPinyinMap.put("Changsha", "长沙");
        cityPinyinMap.put("Zhuzhou", "株洲");
        cityPinyinMap.put("Xiangtan", "湘潭");
        cityPinyinMap.put("Hengyang", "衡阳");
        cityPinyinMap.put("Yueyang", "岳阳");
        cityPinyinMap.put("Chenzhou", "郴州");
        cityPinyinMap.put("Yongzhou", "永州");
        cityPinyinMap.put("Shaoyang", "邵阳");
        cityPinyinMap.put("Huaihua", "怀化");
        cityPinyinMap.put("Changde", "常德");
        cityPinyinMap.put("Yiyang", "益阳");
        cityPinyinMap.put("Zhangjiajie", "张家界");
        cityPinyinMap.put("Loudi", "娄底");
        cityPinyinMap.put("Wuhan", "武汉");
        cityPinyinMap.put("Xiangfan", "襄樊");
        cityPinyinMap.put("Yichang", "宜昌");
        cityPinyinMap.put("Huangshi", "黄石");
        cityPinyinMap.put("Ezhou", "鄂州");
        cityPinyinMap.put("Suizhou", "随州");
        cityPinyinMap.put("Jingzhou", "荆州");
        cityPinyinMap.put("Jingmen", "荆门");
        cityPinyinMap.put("Shiyan", "十堰");
        cityPinyinMap.put("Xiaogan", "孝感");
        cityPinyinMap.put("Huanggang", "黄冈");
        cityPinyinMap.put("Xianning", "咸宁");
        cityPinyinMap.put("Zhengzhou", "郑州");
        cityPinyinMap.put("Luoyang", "洛阳");
        cityPinyinMap.put("Kaifeng", "开封");
        cityPinyinMap.put("Luohe", "漯河");
        cityPinyinMap.put("Anyang", "安阳");
        cityPinyinMap.put("Xinxiang", "新乡");
        cityPinyinMap.put("Zhoukou", "周口");
        cityPinyinMap.put("Sanmenxia", "三门峡");
        cityPinyinMap.put("Jiaozuo", "焦作");
        cityPinyinMap.put("Pingdingshan", "平顶山");
        cityPinyinMap.put("Xinyang", "信阳");
        cityPinyinMap.put("Nanyang", "南阳");
        cityPinyinMap.put("Hebi", "鹤壁");
        cityPinyinMap.put("Puyang", "濮阳");
        cityPinyinMap.put("Xuchang", "许昌");
        cityPinyinMap.put("Shangqiu", "商丘");
        cityPinyinMap.put("Zhumadian", "驻马店");
        cityPinyinMap.put("Taiyuan", "太原");
        cityPinyinMap.put("DaTong", "大同");
        cityPinyinMap.put("Xinzhou", "忻州");
        cityPinyinMap.put("Yangquan", "阳泉");
        cityPinyinMap.put("Changzhi", "长治");
        cityPinyinMap.put("Jincheng", "晋城");
        cityPinyinMap.put("Shuozhou", "朔州");
        cityPinyinMap.put("Jinzhong", "晋中");
        cityPinyinMap.put("Yuncheng", "运城");
        cityPinyinMap.put("Linfen", "临汾");
        cityPinyinMap.put("Lvliang", "吕梁");
        cityPinyinMap.put("Xian", "西安");
        cityPinyinMap.put("Xianyang", "咸阳");
        cityPinyinMap.put("Tongchuan", "铜川");
        cityPinyinMap.put("Yanan", "延安");
        cityPinyinMap.put("Baoji", "宝鸡");
        cityPinyinMap.put("Weinan", "渭南");
        cityPinyinMap.put("Hanzhoung", "汉中");
        cityPinyinMap.put("Ankang", "安康");
        cityPinyinMap.put("Shangluo", "商洛");
        cityPinyinMap.put("Yulin", "榆林");
        cityPinyinMap.put("Lanzhou", "兰州");
        cityPinyinMap.put("Tianshui", "天水");
        cityPinyinMap.put("Pingliang", "平凉");
        cityPinyinMap.put("Jiuquan", "酒泉");
        cityPinyinMap.put("Jiayuguan", "嘉峪关");
        cityPinyinMap.put("Jinchang", "金昌");
        cityPinyinMap.put("baiyiin", "白银");
        cityPinyinMap.put("Wuwei", "武威");
        cityPinyinMap.put("Zhangye", "张掖");
        cityPinyinMap.put("Qingyang", "庆阳");
        cityPinyinMap.put("Dingxi", "定西");
        cityPinyinMap.put("Longnan", "陇南");
        cityPinyinMap.put("Xining", "西宁");
        cityPinyinMap.put("Nanchang", "南昌");
        cityPinyinMap.put("Jiujiang", "九江");
        cityPinyinMap.put("Ganzhou", "赣州");
        cityPinyinMap.put("Jian", "吉安");
        cityPinyinMap.put("Yingtan", "鹰潭");
        cityPinyinMap.put("Shangrao", "上饶");
        cityPinyinMap.put("Pingxiang", "萍乡");
        cityPinyinMap.put("Jingdezhen", "景德镇");
        cityPinyinMap.put("Xinyu", "新余");
        cityPinyinMap.put("Yichun", "宜春");
        cityPinyinMap.put("Fuzhou", "抚州");

    }

    /**
     * 解析拼音城市名：Shenzhen-深圳
     * @param cityPinyin
     * @return
     */
    public static String parseCityPinyin(String cityPinyin) {
        return cityPinyinMap.get(cityPinyin);
    }

    /**
     * 解析站大爷城市名
     * <br/>示例：广东省深圳市 阿里云
     * @param location
     * @return
     */
    public static String parseZdayeCity(String location) {
        String city = "Unknown";
        if (location != null) {
            String[] parts = location.split(" ");
            if (parts.length > 1) {
                city = parts[0].substring(parts[0].indexOf("省") + 1);
            } else {
                city = parts[0];
            }
            city = city.substring(0, city.indexOf("市") + 1);
        }

        return city;
    }
}
