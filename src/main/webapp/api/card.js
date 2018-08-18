/**
 * @api  {get} v3/card/isBindCard 是否绑卡接口
 * @apiVersion 0.1.0
 * @apiname isBindCard
 * @apiGroup card
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 *
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 *
 *
 * @apiSuccessExample {json} Success Example:
 * {
 *   "success": true,
 *   "message": "",
 *   "data": {"isBindCard":true}
 * }
 */


/**
 * @api  {post} v3/card/toBind 绑卡申请
 * @apiVersion 0.1.0
 * @apiname toBind
 * @apiGroup card
 * @apiParam {String="MOBILE"} mediaType 绑卡类型
 * @apiParam {String} mediaId 手机号
 * @apiParam {String} mobile 手机号
 * @apiParam {String} cardId 卡号
 * @apiParam {String} identityCode 身份证号
 * @apiParam {String} cardHolder 持卡人姓名
 * @apiParam {String} province 省
 * @apiParam {String} city 市
 * @apiParam {String} bankBranch  支行
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 * @apiParamExample {json} Example:

     bankBranch = "中国建设银行总行(不受理个人业务)";
     cardHolder = "张冰冰";
     cardId = 123665589666444;
     city = "北京市";
     identityCode = 412825199202026752;
     mediaId = 18458891234;
     mobile = 18458891231;
     province = "北京市";

 *
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 * @apiSuccess {String} userBankCardId   用户绑卡ID
 * @apiSuccess {String} bindNo   绑卡申请号
 *
 *
 * @apiSuccessExample {json} Success Example:
 * {
 *   "success": true,
 *   "message": "",
 *   "data": [{"result":true,"userBankCardId":"515","bindNo":"123123123132"}]
 * }
 */


/**
 * @api  {post} v3/card/bindConfirm 绑卡确认
 * @apiVersion 0.1.0
 * @apiname bindConfirm
 * @apiGroup card
 * @apiParam {String} id 银行卡ID
 *
 * @apiParam {String="MOBILE"} mediaType 绑卡类型
 * @apiParam {String} mediaId 手机号
 * @apiParam {String} mobile 手机号
 * @apiParam {String} cardId 卡号
 * @apiParam {String} identityCode 身份证号
 * @apiParam {String} cardHolder 持卡人姓名
 * @apiParam {String} province 省
 * @apiParam {String} city 市
 * @apiParam {String} bankBranch  支行
 *
 * @apiParam {String} bindNo 绑定单号
 * @apiParam {String} verifyCode 短信验证码
 *
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 *
 * @apiParamExample {json} Example:

 bankBranch = "中国建设银行总行(不受理个人业务)";
 cardHolder = "张冰冰";
 cardId = 123665589666444;
 city = "北京市";
 identityCode = 412825199202026752;
 mediaId = 18458891234;
 mobile = 18458891231;
 province = "北京市";
 bindNo = "1111111111111111111";
 verifyCode = "123456";

 *
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 *
 *
 * @apiSuccessExample {json} Success Example:
 * {
 *   "success": true,
 *   "message": "",
 *   "data": [{"error":null,"message":"success"}]
 * }
 */

/**
 * @api  {get} v3/card/banks 获取支持银行信息
 * @apiVersion 0.1.0
 * @apiname banks
 * @apiGroup card
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 *
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 *
 *
 * @apiSuccessExample {json} Success Example:
 {
     "success": true,
     "message": "成功",
     "data": [
         {
             "id": 125,
             "codeKey": "bank_branch",
             "codeNo": "105",
             "codeName": "建设银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 126,
             "codeKey": "bank_branch",
             "codeNo": "103",
             "codeName": "农业银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 127,
             "codeKey": "bank_branch",
             "codeNo": "104",
             "codeName": "中国银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 128,
             "codeKey": "bank_branch",
             "codeNo": "303",
             "codeName": "光大银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 129,
             "codeKey": "bank_branch",
             "codeNo": "310",
             "codeName": "浦发银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 130,
             "codeKey": "bank_branch",
             "codeNo": "102",
             "codeName": "工商银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 131,
             "codeKey": "bank_branch",
             "codeNo": "301",
             "codeName": "交通银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 132,
             "codeKey": "bank_branch",
             "codeNo": "305",
             "codeName": "民生银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 133,
             "codeKey": "bank_branch",
             "codeNo": "306",
             "codeName": "广发银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         },
         {
             "id": 134,
             "codeKey": "bank_branch",
             "codeNo": "307",
             "codeName": "平安银行",
             "langType": "zh_CN",
             "sortNo": 0,
             "ver": 1
         }
     ]
 }
 */

/**
 * @api  {get} v3/card/bankCity 获取银行城市信息接口
 * @apiVersion 0.1.0
 * @apiname bankCity
 * @apiGroup card
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 *
 * @apiParam {String} provinceCode 省份编号
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 *
 *
 * @apiSuccessExample {json} Success Example:
 {
     "success": true,
     "message": "成功",
     "data": {
         "province": [
             {
                 "code": "11",
                 "name": "北京市",
                 "status": "0"
             },
             {
                 "code": "12",
                 "name": "天津市",
                 "status": "0"
             },
             {
                 "code": "13",
                 "name": "河北省",
                 "status": "0"
             },
             {
                 "code": "14",
                 "name": "山西省",
                 "status": "0"
             },
             {
                 "code": "15",
                 "name": "内蒙古自治区",
                 "status": "0"
             },
             {
                 "code": "21",
                 "name": "辽宁省",
                 "status": "0"
             },
             {
                 "code": "22",
                 "name": "吉林省",
                 "status": "0"
             },
             {
                 "code": "23",
                 "name": "黑龙江省",
                 "status": "0"
             },
             {
                 "code": "31",
                 "name": "上海市",
                 "status": "0"
             },
             {
                 "code": "32",
                 "name": "江苏省",
                 "status": "0"
             },
             {
                 "code": "33",
                 "name": "浙江省",
                 "status": "0"
             },
             {
                 "code": "34",
                 "name": "安徽省",
                 "status": "0"
             },
             {
                 "code": "35",
                 "name": "福建省",
                 "status": "0"
             },
             {
                 "code": "36",
                 "name": "江西省",
                 "status": "0"
             },
             {
                 "code": "37",
                 "name": "山东省",
                 "status": "0"
             },
             {
                 "code": "41",
                 "name": "河南省",
                 "status": "0"
             },
             {
                 "code": "42",
                 "name": "湖北省",
                 "status": "0"
             },
             {
                 "code": "43",
                 "name": "湖南省",
                 "status": "0"
             },
             {
                 "code": "44",
                 "name": "广东省",
                 "status": "0"
             },
             {
                 "code": "45",
                 "name": "广西壮族自治区",
                 "status": "0"
             },
             {
                 "code": "46",
                 "name": "海南省",
                 "status": "0"
             },
             {
                 "code": "50",
                 "name": "重庆市",
                 "status": "0"
             },
             {
                 "code": "51",
                 "name": "四川省",
                 "status": "0"
             },
             {
                 "code": "52",
                 "name": "贵州省",
                 "status": "0"
             },
             {
                 "code": "53",
                 "name": "云南省",
                 "status": "0"
             },
             {
                 "code": "54",
                 "name": "西藏自治区",
                 "status": "0"
             },
             {
                 "code": "61",
                 "name": "陕西省",
                 "status": "0"
             },
             {
                 "code": "62",
                 "name": "甘肃省",
                 "status": "0"
             },
             {
                 "code": "63",
                 "name": "青海省",
                 "status": "0"
             },
             {
                 "code": "64",
                 "name": "宁夏回族自治区",
                 "status": "0"
             },
             {
                 "code": "65",
                 "name": "新疆维吾尔自治区",
                 "status": "0"
             },
             {
                 "code": "71",
                 "name": "台湾省",
                 "status": "0"
             },
             {
                 "code": "81",
                 "name": "香港特别行政区",
                 "status": "0"
             },
             {
                 "code": "82",
                 "name": "澳门特别行政区",
                 "status": "0"
             }
         ],
         "city": [
             {
                 "code": "3301",
                 "provinceCode": "33",
                 "name": "杭州市",
                 "isHot": 1,
                 "searchKey": "hangzhou"
             },
             {
                 "code": "3302",
                 "provinceCode": "33",
                 "name": "宁波市",
                 "isHot": 0,
                 "searchKey": "ningbo"
             },
             {
                 "code": "3303",
                 "provinceCode": "33",
                 "name": "温州市",
                 "isHot": 0,
                 "searchKey": "wenzhou"
             },
             {
                 "code": "3304",
                 "provinceCode": "33",
                 "name": "嘉兴市",
                 "isHot": 0,
                 "searchKey": "jiaxing"
             },
             {
                 "code": "3305",
                 "provinceCode": "33",
                 "name": "湖州市",
                 "isHot": 0,
                 "searchKey": "huzhou"
             },
             {
                 "code": "3306",
                 "provinceCode": "33",
                 "name": "绍兴市",
                 "isHot": 0,
                 "searchKey": "shaoxing"
             },
             {
                 "code": "3307",
                 "provinceCode": "33",
                 "name": "金华市",
                 "isHot": 0,
                 "searchKey": "jinhua"
             },
             {
                 "code": "3308",
                 "provinceCode": "33",
                 "name": "衢州市",
                 "isHot": 0,
                 "searchKey": "quzhou"
             },
             {
                 "code": "3309",
                 "provinceCode": "33",
                 "name": "舟山市",
                 "isHot": 0,
                 "searchKey": "zhoushan"
             },
             {
                 "code": "3310",
                 "provinceCode": "33",
                 "name": "台州市",
                 "isHot": 0,
                 "searchKey": "taizhou"
             },
             {
                 "code": "3311",
                 "provinceCode": "33",
                 "name": "丽水市",
                 "isHot": 0,
                 "searchKey": "lishui"
             }
         ]
     }
 }
 */


/**
 * @api  {get} v3/card/bankBranches 获取银行支行信息接口
 * @apiVersion 0.1.0
 * @apiname bankBranches
 * @apiGroup card
 * @apiHeader {String} Authorization 认证头信息: Bearer {access_token}
 * @apiHeaderExample {String} Head Example:
 * Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5
 *
 * @apiParam {String} cityCode 城市
 * @apiParam {String} bankCode 银行编号
 * @apiSuccess {String}   success   正确错误
 * @apiSuccess {String}   message   消息
 * @apiSuccess {Object} data   返回数据
 *
 *
 * @apiSuccessExample {json} Success Example:
 {
     "success": true,
     "message": "成功",
     "data": [
         {
             "code": "105331000000",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行"
         },
         {
             "code": "105331002005",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105331000000",
             "name": "中国建设银行浙江省分行营业部"
         },
         {
             "code": "105331002030",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州星都支行"
         },
         {
             "code": "105331002048",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州宝石支行留下分理处"
         },
         {
             "code": "105331002056",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州安吉路支行"
         },
         {
             "code": "105331003008",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行吴山支行"
         },
         {
             "code": "105331003024",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州建国路支行"
         },
         {
             "code": "105331003032",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行吴山支行梅花碑分理处"
         },
         {
             "code": "105331003049",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州解放路支行"
         },
         {
             "code": "105331003057",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州雄镇楼支行"
         },
         {
             "code": "105331003065",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行吴山支行国安分理处"
         },
         {
             "code": "105331003073",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州杭大路分理处"
         },
         {
             "code": "105331003090",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股分有限公司杭州中河路分理处"
         },
         {
             "code": "105331003104",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州美政桥支行"
         },
         {
             "code": "105331003112",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州河坊街支行"
         },
         {
             "code": "105331004003",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州天水支行"
         },
         {
             "code": "105331004020",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州体育场路支行"
         },
         {
             "code": "105331004038",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州莫干山路支行"
         },
         {
             "code": "105331004046",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州德新支行"
         },
         {
             "code": "105331004054",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州象山路支行"
         },
         {
             "code": "105331004062",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州石桥分理处"
         },
         {
             "code": "105331004079",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州东站支行"
         },
         {
             "code": "105331004087",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州潮鸣支行"
         },
         {
             "code": "105331004095",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州朝晖分理处"
         },
         {
             "code": "105331004100",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州潮王路支行"
         },
         {
             "code": "105331005006",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行文晖支行"
         },
         {
             "code": "105331005022",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州拱宸桥支行"
         },
         {
             "code": "105331005039",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州拱北支行"
         },
         {
             "code": "105331005047",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州环北支行"
         },
         {
             "code": "105331005055",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州大关支行"
         },
         {
             "code": "105331005063",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州仓基上分理处"
         },
         {
             "code": "105331005071",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州东新园支行"
         },
         {
             "code": "105331005080",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州钱江分理处"
         },
         {
             "code": "105331005098",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州名仕分理处"
         },
         {
             "code": "105331005102",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州施家桥分理处"
         },
         {
             "code": "105331005119",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州申花路支行"
         },
         {
             "code": "105331006009",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州秋涛支行"
         },
         {
             "code": "105331006025",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州城站支行"
         },
         {
             "code": "105331006033",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州天成路支行"
         },
         {
             "code": "105331006041",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州凯旋路分理处"
         },
         {
             "code": "105331006050",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州通江桥支行"
         },
         {
             "code": "105331006068",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州彩虹城支行"
         },
         {
             "code": "105331006076",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州江城支行"
         },
         {
             "code": "105331006084",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州塘苗分理处"
         },
         {
             "code": "105331006092",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州杭海路支行"
         },
         {
             "code": "105331006105",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州兴安支行"
         },
         {
             "code": "105331006113",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州德胜东路支行"
         },
         {
             "code": "105331006121",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州城中支行"
         },
         {
             "code": "105331007001",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州钱江支行"
         },
         {
             "code": "105331007028",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州绍兴路支行"
         },
         {
             "code": "105331007036",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州半山支行"
         },
         {
             "code": "105331007044",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105331000000",
             "name": "中国建设银行浙江省分行高新支行滨江分理处"
         },
         {
             "code": "105331007052",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州新塘路支行"
         },
         {
             "code": "105331007069",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州古墩路支行"
         },
         {
             "code": "105331007077",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州丁桥支行"
         },
         {
             "code": "105331008004",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州高新支行"
         },
         {
             "code": "105331008029",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州文西支行"
         },
         {
             "code": "105331008037",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州文华路支行"
         },
         {
             "code": "105331008045",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州三墩支行"
         },
         {
             "code": "105331008053",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州华星支行"
         },
         {
             "code": "105331008061",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州文欣分理处"
         },
         {
             "code": "105331008070",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州学院路分理处"
         },
         {
             "code": "105331008088",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州翠苑分理处"
         },
         {
             "code": "105331008096",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州竞舟路支行"
         },
         {
             "code": "105331008107",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州玉古路支行"
         },
         {
             "code": "105331008115",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州转塘支行"
         },
         {
             "code": "105331008123",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州文新支行"
         },
         {
             "code": "105331008131",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州天目山路分理处"
         },
         {
             "code": "105331008140",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州文三路支行"
         },
         {
             "code": "105331009007",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州经济技术开发区支行"
         },
         {
             "code": "105331009023",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州九堡支行"
         },
         {
             "code": "105331009031",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建行银行股份有限公司杭州彭埠支行"
         },
         {
             "code": "105331009040",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州金沙湖支行"
         },
         {
             "code": "105331010005",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州宝石支行"
         },
         {
             "code": "105331010021",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州河东路支行"
         },
         {
             "code": "105331010030",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州浙大支行"
         },
         {
             "code": "105331010048",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州玉泉支行"
         },
         {
             "code": "105331011008",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州延安支行"
         },
         {
             "code": "105331012003",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州中山支行"
         },
         {
             "code": "105331013006",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行庆春支行"
         },
         {
             "code": "105331013022",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州民安支行"
         },
         {
             "code": "105331013047",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州鲲鹏支行"
         },
         {
             "code": "105331014009",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行杭州滨江支行"
         },
         {
             "code": "105331014017",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州江南支行"
         },
         {
             "code": "105331014025",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州浦沿支行"
         },
         {
             "code": "105331014033",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州白马湖支行"
         },
         {
             "code": "105331014041",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州东冠路支行"
         },
         {
             "code": "105331014050",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州江陵路支行"
         },
         {
             "code": "105331015001",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州西湖支行"
         },
         {
             "code": "105331016004",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州之江支行"
         },
         {
             "code": "105331017015",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州钱塘支行"
         },
         {
             "code": "105331017023",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州飞云江路支行"
         },
         {
             "code": "105331017031",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萍水路支行"
         },
         {
             "code": "105331021009",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭支行"
         },
         {
             "code": "105331021025",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州太炎支行"
         },
         {
             "code": "105331021033",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州塘栖支行"
         },
         {
             "code": "105331021041",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州乔司支行"
         },
         {
             "code": "105331021050",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州闲林支行"
         },
         {
             "code": "105331021068",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州瓶窑支行"
         },
         {
             "code": "105331021076",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州临平支行"
         },
         {
             "code": "105331021084",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭城北支行"
         },
         {
             "code": "105331021092",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭东方支行"
         },
         {
             "code": "105331021105",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州星桥支行"
         },
         {
             "code": "105331021113",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭良渚分理处"
         },
         {
             "code": "105331021121",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭保健路支行"
         },
         {
             "code": "105331021130",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州勾庄支行"
         },
         {
             "code": "105331021148",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭勾庄分理处"
         },
         {
             "code": "105331021156",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州崇贤支行"
         },
         {
             "code": "105331021164",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州余杭连城支行"
         },
         {
             "code": "105331021172",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州西溪支行"
         },
         {
             "code": "105331022001",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山支行"
         },
         {
             "code": "105331022028",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州临浦支行"
         },
         {
             "code": "105331022036",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山新世纪广场支行"
         },
         {
             "code": "105331022044",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州党湾支行"
         },
         {
             "code": "105331022052",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山开发区支行"
         },
         {
             "code": "105331022069",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州瓜沥支行"
         },
         {
             "code": "105331022077",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山商业城支行"
         },
         {
             "code": "105331022085",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山时代广场支行"
         },
         {
             "code": "105331022093",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山财富支行"
         },
         {
             "code": "105331022108",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山金城路支行"
         },
         {
             "code": "105331022116",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州新街支行"
         },
         {
             "code": "105331022124",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州闻堰支行"
         },
         {
             "code": "105331022132",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山潘水路支行"
         },
         {
             "code": "105331022149",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山育才路支行"
         },
         {
             "code": "105331022157",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州衙前支行"
         },
         {
             "code": "105331022165",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山通惠路支行"
         },
         {
             "code": "105331022181",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州宁围支行"
         },
         {
             "code": "105331022204",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州义蓬支行"
         },
         {
             "code": "105331022212",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州党山支行"
         },
         {
             "code": "105331022229",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州临江支行"
         },
         {
             "code": "105331022237",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州萧山新农都市场支行"
         },
         {
             "code": "105331023004",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行富阳支行"
         },
         {
             "code": "105331023029",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳新登支行"
         },
         {
             "code": "105331023037",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳桂花西路支行"
         },
         {
             "code": "105331023045",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳高桥支行"
         },
         {
             "code": "105331023053",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳支行龙山路分理处"
         },
         {
             "code": "105331023061",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳金桥支行"
         },
         {
             "code": "105331023070",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳支行春江分理处"
         },
         {
             "code": "105331023088",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳支行田园路分理处"
         },
         {
             "code": "105331023107",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司富阳城北支行"
         },
         {
             "code": "105331024007",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行临安支行"
         },
         {
             "code": "105331024023",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司临安锦城支行"
         },
         {
             "code": "105331024031",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司临安於潜分理处"
         },
         {
             "code": "105331024040",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行临安支行昌化分理处"
         },
         {
             "code": "105331024058",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司杭州航空港支行"
         },
         {
             "code": "105331024066",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行临安支行万事达分理处"
         },
         {
             "code": "105331024074",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行临安支行经济开发区分理处"
         },
         {
             "code": "105331024082",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司临安高虹支行"
         },
         {
             "code": "105331024099",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司临安喜运来分理处"
         },
         {
             "code": "105331024103",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司临安府前路支行"
         },
         {
             "code": "105331025000",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐支行"
         },
         {
             "code": "105331025026",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐富春江支行"
         },
         {
             "code": "105331025034",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐江南支行"
         },
         {
             "code": "105331025042",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐横村分理处"
         },
         {
             "code": "105331025059",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐分水支行"
         },
         {
             "code": "105331025067",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司桐庐新区支行"
         },
         {
             "code": "105331026002",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行建德支行"
         },
         {
             "code": "105331026027",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德寿昌支行"
         },
         {
             "code": "105331026035",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德梅城支行"
         },
         {
             "code": "105331026043",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德乾潭支行"
         },
         {
             "code": "105331026051",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德新安江支行"
         },
         {
             "code": "105331026060",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德新广场支行"
         },
         {
             "code": "105331026078",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司建德大同支行"
         },
         {
             "code": "105331027005",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行浙江省分行淳安支行"
         },
         {
             "code": "105331027021",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司淳安城中支行"
         },
         {
             "code": "105331027030",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司淳安湖光支行"
         },
         {
             "code": "105331081157",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105331000000",
             "name": "中国建设银行股份有限公司杭州转塘支行"
         },
         {
             "code": "105331088888",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105100000017",
             "name": "中国建设银行股份有限公司浙江省分行营业部"
         },
         {
             "code": "105331099997",
             "bankCode": "105",
             "cityCode": "3301",
             "pbcNo": "105331000000",
             "name": "中国建设银行浙江省分行营业部运行中心"
         }
     ]
 }
 */