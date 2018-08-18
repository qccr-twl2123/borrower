define({ "api": [
  {
    "type": "post",
    "url": "v3/order/finish",
    "title": "订单完成提交",
    "version": "0.1.0",
    "name": "applyAdd",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "allowedValues": [
              "\"application/json\""
            ],
            "optional": false,
            "field": "Content-Type",
            "description": ""
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>商户订单号，保证唯一</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"C12\""
            ],
            "optional": false,
            "field": "productCode",
            "description": "<p>合作产品码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityId",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "corpName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Parameter",
            "type": "Object[]",
            "optional": false,
            "field": "contacts",
            "description": "<p>联系人信息</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "contacts.name",
            "description": "<p>联系人姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "contacts.mobile",
            "description": "<p>联系人手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "contacts.relation",
            "description": "<p>联系人关系</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Example:",
          "content": "{\"productCode\":\"C12\",\"contacts\":[{\"name\":\"同一1\",\"mobile\":\"13429151111\",\"relation\":\"子女\"},{\"name\":\"同一2\",\"mobile\":\"13429151112\",\"relation\":\"朋友\"},{\"name\":\"同一3\",\"mobile\":\"13429151113\",\"relation\":\"同事\"}],\"name\":\"老五\",\"mobile\":\"13429151266\",\"identityId\":\"430426198609166173\",\"orderNo\":\"ODNO000101010100101\"}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "data",
            "description": "<p>true 成功 false 失败</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.applyId",
            "description": "<p>投融家申请ID</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data.status",
            "description": "<p>申请状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"成功\",\n  \"data\": true\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "post",
    "url": "v3/order/doSignature",
    "title": "合同签章接口",
    "version": "0.1.0",
    "name": "doSignature",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>商户订单号，保证唯一</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "verifyCode",
            "description": "<p>短信验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "useAmount",
            "description": "<p>提交金额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loanUseType",
            "description": "<p>借款用途</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "cutCharge",
            "description": "<p>吹头息金额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "term",
            "description": "<p>期数</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "interest",
            "description": "<p>总费率</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "bankCardId",
            "description": "<p>放款银行卡ID</p>"
          },
          {
            "group": "Parameter",
            "type": "BigDecimal",
            "optional": false,
            "field": "expectRepayAmount",
            "description": "<p>预计每月还款金额</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.boolean",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/getLimitAuditList",
    "title": "获取额度批复列表信息",
    "version": "0.1.0",
    "name": "getLimitAuditList",
    "group": "Apply",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"C12\""
            ],
            "optional": false,
            "field": "productCode",
            "description": "<p>合作产品码</p>"
          }
        ]
      }
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n    \"success\": true,\n    \"message\": \"获取额度批复列表信息成功！\",\n    \"data\": {\n        \"limit\": 10,\n        \"totalPages\": 1,\n        \"page\": 1,\n        \"list\": [\n            {\n                \"applyId\": 3152,\n                \"uid\": 547,\n                \"productId\": 1,\n                \"productCode\": \"C9\",\n                \"productType\": 1,\n                \"productName\": \"工薪贷\",\n                \"repayType\": \"permonth\",\n                \"status\": 2,\n                \"applyAmount\": 5000,\n                \"applyTime\": \"2017/7/24\",\n                \"auditTime\": null,\n                \"auditAmount\": 99000,\n                \"restLimit\": 99000,\n                \"vailDate\": \"2017/8/23\",\n                \"loanNumber\": 0,\n                \"loanAmount\": 0,\n                \"showStatus\": 3,\n                \"amountLimitCalculate1\": 99000,\n                \"amountLimitCalculate2\": null,\n                \"term1\": \"6,12,18,24\",\n                \"term1List\": [\n                    \"6个月\",\n                    \"12个月\",\n                    \"18个月\",\n                    \"24个月\"\n                ],\n                \"term2\": null,\n                \"term2List\": null,\n                \"termUnit\": \"month\",\n                \"interest\": 0.022,\n                \"rateType\": 0,\n                \"repayTypeList\": [\n                    {\n                        \"id\": 97,\n                        \"codeKey\": \"repay_type\",\n                        \"codeNo\": \"permonth\",\n                        \"codeName\": \"每月等额还款\",\n                        \"langType\": \"zh_CN\",\n                        \"sortNo\": 1,\n                        \"ver\": 1\n                    }\n                ],\n                \"curTermRate\": {\n                    \"6\": \"0.03\",\n                    \"12\": \"0.05\",\n                    \"18\": \"0.07\",\n                    \"24\": \"0.08\"\n                }\n            }\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/getRepayPlans",
    "title": "获取还款计划列表信息",
    "version": "0.1.0",
    "name": "getRepayPlans",
    "group": "Apply",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>商户订单号</p>"
          }
        ]
      }
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.list.periodNumber",
            "description": "<p>还款期数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.list.repayDate",
            "description": "<p>还款日期</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.list.repayAmount",
            "description": "<p>还款金额</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.list.status",
            "description": "<p>还款状态（0未还，1部分还款，2已还）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "data.list.statusDescribe",
            "description": "<p>还款状态描述</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n    \"success\": true,\n    \"message\": \"获取还款计划列表信息成功！\",\n    \"data\": {\n        \"limit\": 10,\n        \"totalPages\": 1,\n        \"page\": 1,\n        \"list\": [\n            {\n                \"periodNumber\": \"1\",\n                \"repayDate\": \"2017/10/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            },\n            {\n                \"periodNumber\": \"2\",\n                \"repayDate\": \"2017/11/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            },\n            {\n                \"periodNumber\": \"3\",\n                \"repayDate\": \"2017/12/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            },\n            {\n                \"periodNumber\": \"4\",\n                \"repayDate\": \"2018/01/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            },\n            {\n                \"periodNumber\": \"5\",\n                \"repayDate\": \"2018/02/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            },\n            {\n                \"periodNumber\": \"6\",\n                \"repayDate\": \"2018/03/02\",\n                \"repayAmount\": \"3733.33\",\n                \"status\": 0,\n                \"statusDescribe\": \"未还\",\n                \"repayedAmount\": null\n            }\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/apply/isBlacklist",
    "title": "黑名单检测",
    "version": "0.1.0",
    "name": "isBlacklist",
    "group": "Apply",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityId",
            "description": "<p>身份证号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "boolean",
            "optional": false,
            "field": "isBlacklist",
            "description": "<p>黑名单：true, 不是黑名单：false</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": {\"isBlacklist\":false}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/queryInsuranceContractTpl",
    "title": "查询保险协议",
    "version": "0.1.0",
    "name": "queryInsuranceContractTpl",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "\n{\n    \"data\":[\n        \"http://testjkweb.tourongjia.com/templates/保险条款.pdf\",\n        \"http://testjkweb.tourongjia.com/templates/投保须知.pdf\"\n    ],\n    \"message\":\"成功\",\n    \"success\":true\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/queryLoanUseTypeList",
    "title": "查询借款用途",
    "version": "0.1.0",
    "name": "queryLoanUseTypeList",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": " {\n    \"data\":[\n        \"婚庆服务\",\n        \"旅游消费\",\n        \"购车消费\",\n        \"助学进修\",\n        \"百货家电\",\n        \"医疗服务\",\n        \"装修建材\"\n    ],\n    \"message\":\"成功\",\n    \"success\":true\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/signatureAccredit",
    "title": "即电子签章发送验证码接口",
    "version": "0.1.0",
    "name": "signatureAccredit",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\":\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "type": "get",
    "url": "v3/order/tryDoRepayAmount",
    "title": "还款试算",
    "version": "0.1.0",
    "name": "tryDoRepayAmount",
    "group": "Apply",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "term",
            "description": "<p>还款期数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "amount",
            "description": "<p>贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "interest",
            "description": "<p>利率</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.String",
            "optional": false,
            "field": "cutAmount",
            "description": "<p>保险金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.String",
            "optional": false,
            "field": "limitAmount",
            "description": "<p>可用金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.list",
            "optional": false,
            "field": "termList",
            "description": "<p>还款计算列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.list.term",
            "optional": false,
            "field": "termList.term",
            "description": "<p>期数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object.list.termAmount",
            "optional": false,
            "field": "termList.termAmount",
            "description": "<p>还款金额</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": " {\n    \"data\":{\n        \"cutAmount\":\"8.00\",\n        \"interest\":\"0.1\",\n        \"limitAmount\":\"992.00\",\n        \"term\":12,\n        \"termList\":[\n            {\n                \"term\":1,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":2,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":3,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":4,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":5,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":6,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":7,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":8,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":9,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":10,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":11,\n                \"termAmount\":\"183.33\"\n            },\n            {\n                \"term\":12,\n                \"termAmount\":\"183.33\"\n            }\n        ]\n    },\n    \"message\":\"成功\",\n    \"success\":true\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "Apply"
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/doc/main.js",
    "group": "E__project_trj_jk_web_src_main_webapp_api_doc_main_js",
    "groupTitle": "E__project_trj_jk_web_src_main_webapp_api_doc_main_js",
    "name": ""
  },
  {
    "type": "post",
    "url": "oauth/token",
    "title": "获取accessToken",
    "version": "1.0.0",
    "name": "getAccessToken",
    "group": "OAuth2",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: 使用Basic认证，CFD会提供给调用方用户名，密码(client_id和client_secret)，比如: 用户名为aaa,密码为123，使用Base64加密伪代码为 base64(&quot;aaa:123&quot;)，结果为 YWFhOjEyMw==</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Basic YWFhOjEyMw==",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"password\""
            ],
            "optional": false,
            "field": "grant_type",
            "description": "<p>授权类型: password</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>对应手机号(授权前需要先使用提交基本信息接口/v3/apply/add 形成用户后才能使用)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码（会给默认密码）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Example: ",
          "content": "grant_type=password\nusername=手机号\npassword=*****",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "access_token",
            "description": "<p>Access Token</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "token_type",
            "description": "<p>目前为bearer</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "expires_in",
            "description": "<p>过期剩余时间：单位为秒</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "scope",
            "description": "<p>调用方被授权的域</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"access_token\": \"1d44bad5-2b9e-46e5-bfbe-80a8988b99b7\",\n  \"token_type\": \"bearer\",\n  \"expires_in\": 793,\n  \"scope\": \"write read\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "Unauthorized",
            "description": "<p>认证失败，返回http状态码401</p>"
          }
        ]
      }
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/oauth2-api.js",
    "groupTitle": "OAuth2"
  },
  {
    "type": "post",
    "url": "v3/user/idCardOcr",
    "title": "身份证识别接口",
    "version": "0.1.0",
    "name": "idCardOcr",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "idcardBase64Img",
            "description": "<p>图片base64</p>"
          }
        ]
      }
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": [{\"address\":\"江西省抚州市南丰县白舍镇居民二小区白舍大道1号\",\"birthday\":\"1989年07月08日\",\"cId\":\"36252419890708511X\",\"cName\":\"老王\",\"code\":0,\"faceImage\":\"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\",\"flowId\":654949,\"folk\":\"汉\",\"msg\":\"身份证识别成功\",\"sex\":\"男\",\"type\":1}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "v3/user/identityAuth",
    "title": "身份信息认证和保存",
    "permission": [
      {
        "name": "none"
      }
    ],
    "version": "0.1.0",
    "name": "identityAuthAndSave",
    "group": "User",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityId",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityAddress",
            "description": "<p>身份证地址</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "startDate",
            "description": "<p>有效起始日期</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "endDate",
            "description": "<p>有效结束日期</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityCardFrontImage",
            "description": "<p>身份证正面</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityCardOppositeImage",
            "description": "<p>身份证反面</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "headImage",
            "description": "<p>身份证证抠图照片</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>外部订单号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"保存身份信息成功\",\n  \"data\": null\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "v3/user/livingBodyAuthAndSave",
    "title": "人脸活体比对匹配和保存",
    "version": "0.1.0",
    "name": "livingBodyAuthAndSave",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNo",
            "description": "<p>订单号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityId",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityAddress",
            "description": "<p>身份证地址</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "startDate",
            "description": "<p>有效起始日期</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "endDate",
            "description": "<p>有效结束日期</p>"
          },
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "file",
            "description": "<p>图片文件 1张活体照</p>"
          }
        ]
      }
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": [{\"authResult\":true}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "v3/apply/register",
    "title": "用户注册",
    "version": "0.1.0",
    "name": "register",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: 使用Basic认证，CFD会提供给调用方用户名，密码(client_id和client_secret)，比如: 用户名为aaa,密码为123，使用Base64加密伪代码为 base64(&quot;aaa:123&quot;)，结果为 YWFhOjEyMw==</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Basic YWFhOjEyMw==",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "data",
            "description": "<p>true 成功 false 失败</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": {\"data\":true,\"message\":\"成功\",\"success\":true}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/main.js",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "v3/card/bankBranches",
    "title": "获取银行支行信息接口",
    "version": "0.1.0",
    "name": "bankBranches",
    "group": "card",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cityCode",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankCode",
            "description": "<p>银行编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n    \"success\": true,\n    \"message\": \"成功\",\n    \"data\": [\n        {\n            \"code\": \"105331000000\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行\"\n        },\n        {\n            \"code\": \"105331002005\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105331000000\",\n            \"name\": \"中国建设银行浙江省分行营业部\"\n        },\n        {\n            \"code\": \"105331002030\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州星都支行\"\n        },\n        {\n            \"code\": \"105331002048\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州宝石支行留下分理处\"\n        },\n        {\n            \"code\": \"105331002056\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州安吉路支行\"\n        },\n        {\n            \"code\": \"105331003008\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行吴山支行\"\n        },\n        {\n            \"code\": \"105331003024\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州建国路支行\"\n        },\n        {\n            \"code\": \"105331003032\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行吴山支行梅花碑分理处\"\n        },\n        {\n            \"code\": \"105331003049\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州解放路支行\"\n        },\n        {\n            \"code\": \"105331003057\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州雄镇楼支行\"\n        },\n        {\n            \"code\": \"105331003065\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行吴山支行国安分理处\"\n        },\n        {\n            \"code\": \"105331003073\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州杭大路分理处\"\n        },\n        {\n            \"code\": \"105331003090\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股分有限公司杭州中河路分理处\"\n        },\n        {\n            \"code\": \"105331003104\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州美政桥支行\"\n        },\n        {\n            \"code\": \"105331003112\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州河坊街支行\"\n        },\n        {\n            \"code\": \"105331004003\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州天水支行\"\n        },\n        {\n            \"code\": \"105331004020\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州体育场路支行\"\n        },\n        {\n            \"code\": \"105331004038\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州莫干山路支行\"\n        },\n        {\n            \"code\": \"105331004046\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州德新支行\"\n        },\n        {\n            \"code\": \"105331004054\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州象山路支行\"\n        },\n        {\n            \"code\": \"105331004062\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州石桥分理处\"\n        },\n        {\n            \"code\": \"105331004079\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州东站支行\"\n        },\n        {\n            \"code\": \"105331004087\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州潮鸣支行\"\n        },\n        {\n            \"code\": \"105331004095\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州朝晖分理处\"\n        },\n        {\n            \"code\": \"105331004100\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州潮王路支行\"\n        },\n        {\n            \"code\": \"105331005006\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行文晖支行\"\n        },\n        {\n            \"code\": \"105331005022\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州拱宸桥支行\"\n        },\n        {\n            \"code\": \"105331005039\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州拱北支行\"\n        },\n        {\n            \"code\": \"105331005047\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州环北支行\"\n        },\n        {\n            \"code\": \"105331005055\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州大关支行\"\n        },\n        {\n            \"code\": \"105331005063\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州仓基上分理处\"\n        },\n        {\n            \"code\": \"105331005071\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州东新园支行\"\n        },\n        {\n            \"code\": \"105331005080\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州钱江分理处\"\n        },\n        {\n            \"code\": \"105331005098\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州名仕分理处\"\n        },\n        {\n            \"code\": \"105331005102\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州施家桥分理处\"\n        },\n        {\n            \"code\": \"105331005119\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州申花路支行\"\n        },\n        {\n            \"code\": \"105331006009\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州秋涛支行\"\n        },\n        {\n            \"code\": \"105331006025\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州城站支行\"\n        },\n        {\n            \"code\": \"105331006033\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州天成路支行\"\n        },\n        {\n            \"code\": \"105331006041\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州凯旋路分理处\"\n        },\n        {\n            \"code\": \"105331006050\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州通江桥支行\"\n        },\n        {\n            \"code\": \"105331006068\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州彩虹城支行\"\n        },\n        {\n            \"code\": \"105331006076\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州江城支行\"\n        },\n        {\n            \"code\": \"105331006084\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州塘苗分理处\"\n        },\n        {\n            \"code\": \"105331006092\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州杭海路支行\"\n        },\n        {\n            \"code\": \"105331006105\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州兴安支行\"\n        },\n        {\n            \"code\": \"105331006113\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州德胜东路支行\"\n        },\n        {\n            \"code\": \"105331006121\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州城中支行\"\n        },\n        {\n            \"code\": \"105331007001\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州钱江支行\"\n        },\n        {\n            \"code\": \"105331007028\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州绍兴路支行\"\n        },\n        {\n            \"code\": \"105331007036\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州半山支行\"\n        },\n        {\n            \"code\": \"105331007044\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105331000000\",\n            \"name\": \"中国建设银行浙江省分行高新支行滨江分理处\"\n        },\n        {\n            \"code\": \"105331007052\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州新塘路支行\"\n        },\n        {\n            \"code\": \"105331007069\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州古墩路支行\"\n        },\n        {\n            \"code\": \"105331007077\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州丁桥支行\"\n        },\n        {\n            \"code\": \"105331008004\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州高新支行\"\n        },\n        {\n            \"code\": \"105331008029\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州文西支行\"\n        },\n        {\n            \"code\": \"105331008037\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州文华路支行\"\n        },\n        {\n            \"code\": \"105331008045\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州三墩支行\"\n        },\n        {\n            \"code\": \"105331008053\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州华星支行\"\n        },\n        {\n            \"code\": \"105331008061\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州文欣分理处\"\n        },\n        {\n            \"code\": \"105331008070\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州学院路分理处\"\n        },\n        {\n            \"code\": \"105331008088\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州翠苑分理处\"\n        },\n        {\n            \"code\": \"105331008096\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州竞舟路支行\"\n        },\n        {\n            \"code\": \"105331008107\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州玉古路支行\"\n        },\n        {\n            \"code\": \"105331008115\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州转塘支行\"\n        },\n        {\n            \"code\": \"105331008123\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州文新支行\"\n        },\n        {\n            \"code\": \"105331008131\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州天目山路分理处\"\n        },\n        {\n            \"code\": \"105331008140\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州文三路支行\"\n        },\n        {\n            \"code\": \"105331009007\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州经济技术开发区支行\"\n        },\n        {\n            \"code\": \"105331009023\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州九堡支行\"\n        },\n        {\n            \"code\": \"105331009031\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建行银行股份有限公司杭州彭埠支行\"\n        },\n        {\n            \"code\": \"105331009040\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州金沙湖支行\"\n        },\n        {\n            \"code\": \"105331010005\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州宝石支行\"\n        },\n        {\n            \"code\": \"105331010021\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州河东路支行\"\n        },\n        {\n            \"code\": \"105331010030\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州浙大支行\"\n        },\n        {\n            \"code\": \"105331010048\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州玉泉支行\"\n        },\n        {\n            \"code\": \"105331011008\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州延安支行\"\n        },\n        {\n            \"code\": \"105331012003\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州中山支行\"\n        },\n        {\n            \"code\": \"105331013006\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行庆春支行\"\n        },\n        {\n            \"code\": \"105331013022\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州民安支行\"\n        },\n        {\n            \"code\": \"105331013047\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州鲲鹏支行\"\n        },\n        {\n            \"code\": \"105331014009\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行杭州滨江支行\"\n        },\n        {\n            \"code\": \"105331014017\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州江南支行\"\n        },\n        {\n            \"code\": \"105331014025\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州浦沿支行\"\n        },\n        {\n            \"code\": \"105331014033\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州白马湖支行\"\n        },\n        {\n            \"code\": \"105331014041\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州东冠路支行\"\n        },\n        {\n            \"code\": \"105331014050\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州江陵路支行\"\n        },\n        {\n            \"code\": \"105331015001\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州西湖支行\"\n        },\n        {\n            \"code\": \"105331016004\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州之江支行\"\n        },\n        {\n            \"code\": \"105331017015\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州钱塘支行\"\n        },\n        {\n            \"code\": \"105331017023\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州飞云江路支行\"\n        },\n        {\n            \"code\": \"105331017031\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萍水路支行\"\n        },\n        {\n            \"code\": \"105331021009\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭支行\"\n        },\n        {\n            \"code\": \"105331021025\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州太炎支行\"\n        },\n        {\n            \"code\": \"105331021033\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州塘栖支行\"\n        },\n        {\n            \"code\": \"105331021041\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州乔司支行\"\n        },\n        {\n            \"code\": \"105331021050\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州闲林支行\"\n        },\n        {\n            \"code\": \"105331021068\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州瓶窑支行\"\n        },\n        {\n            \"code\": \"105331021076\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州临平支行\"\n        },\n        {\n            \"code\": \"105331021084\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭城北支行\"\n        },\n        {\n            \"code\": \"105331021092\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭东方支行\"\n        },\n        {\n            \"code\": \"105331021105\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州星桥支行\"\n        },\n        {\n            \"code\": \"105331021113\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭良渚分理处\"\n        },\n        {\n            \"code\": \"105331021121\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭保健路支行\"\n        },\n        {\n            \"code\": \"105331021130\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州勾庄支行\"\n        },\n        {\n            \"code\": \"105331021148\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭勾庄分理处\"\n        },\n        {\n            \"code\": \"105331021156\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州崇贤支行\"\n        },\n        {\n            \"code\": \"105331021164\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州余杭连城支行\"\n        },\n        {\n            \"code\": \"105331021172\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州西溪支行\"\n        },\n        {\n            \"code\": \"105331022001\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山支行\"\n        },\n        {\n            \"code\": \"105331022028\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州临浦支行\"\n        },\n        {\n            \"code\": \"105331022036\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山新世纪广场支行\"\n        },\n        {\n            \"code\": \"105331022044\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州党湾支行\"\n        },\n        {\n            \"code\": \"105331022052\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山开发区支行\"\n        },\n        {\n            \"code\": \"105331022069\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州瓜沥支行\"\n        },\n        {\n            \"code\": \"105331022077\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山商业城支行\"\n        },\n        {\n            \"code\": \"105331022085\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山时代广场支行\"\n        },\n        {\n            \"code\": \"105331022093\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山财富支行\"\n        },\n        {\n            \"code\": \"105331022108\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山金城路支行\"\n        },\n        {\n            \"code\": \"105331022116\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州新街支行\"\n        },\n        {\n            \"code\": \"105331022124\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州闻堰支行\"\n        },\n        {\n            \"code\": \"105331022132\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山潘水路支行\"\n        },\n        {\n            \"code\": \"105331022149\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山育才路支行\"\n        },\n        {\n            \"code\": \"105331022157\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州衙前支行\"\n        },\n        {\n            \"code\": \"105331022165\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山通惠路支行\"\n        },\n        {\n            \"code\": \"105331022181\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州宁围支行\"\n        },\n        {\n            \"code\": \"105331022204\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州义蓬支行\"\n        },\n        {\n            \"code\": \"105331022212\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州党山支行\"\n        },\n        {\n            \"code\": \"105331022229\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州临江支行\"\n        },\n        {\n            \"code\": \"105331022237\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州萧山新农都市场支行\"\n        },\n        {\n            \"code\": \"105331023004\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行富阳支行\"\n        },\n        {\n            \"code\": \"105331023029\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳新登支行\"\n        },\n        {\n            \"code\": \"105331023037\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳桂花西路支行\"\n        },\n        {\n            \"code\": \"105331023045\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳高桥支行\"\n        },\n        {\n            \"code\": \"105331023053\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳支行龙山路分理处\"\n        },\n        {\n            \"code\": \"105331023061\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳金桥支行\"\n        },\n        {\n            \"code\": \"105331023070\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳支行春江分理处\"\n        },\n        {\n            \"code\": \"105331023088\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳支行田园路分理处\"\n        },\n        {\n            \"code\": \"105331023107\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司富阳城北支行\"\n        },\n        {\n            \"code\": \"105331024007\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行临安支行\"\n        },\n        {\n            \"code\": \"105331024023\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司临安锦城支行\"\n        },\n        {\n            \"code\": \"105331024031\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司临安於潜分理处\"\n        },\n        {\n            \"code\": \"105331024040\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行临安支行昌化分理处\"\n        },\n        {\n            \"code\": \"105331024058\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司杭州航空港支行\"\n        },\n        {\n            \"code\": \"105331024066\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行临安支行万事达分理处\"\n        },\n        {\n            \"code\": \"105331024074\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行临安支行经济开发区分理处\"\n        },\n        {\n            \"code\": \"105331024082\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司临安高虹支行\"\n        },\n        {\n            \"code\": \"105331024099\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司临安喜运来分理处\"\n        },\n        {\n            \"code\": \"105331024103\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司临安府前路支行\"\n        },\n        {\n            \"code\": \"105331025000\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐支行\"\n        },\n        {\n            \"code\": \"105331025026\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐富春江支行\"\n        },\n        {\n            \"code\": \"105331025034\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐江南支行\"\n        },\n        {\n            \"code\": \"105331025042\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐横村分理处\"\n        },\n        {\n            \"code\": \"105331025059\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐分水支行\"\n        },\n        {\n            \"code\": \"105331025067\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司桐庐新区支行\"\n        },\n        {\n            \"code\": \"105331026002\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行建德支行\"\n        },\n        {\n            \"code\": \"105331026027\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德寿昌支行\"\n        },\n        {\n            \"code\": \"105331026035\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德梅城支行\"\n        },\n        {\n            \"code\": \"105331026043\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德乾潭支行\"\n        },\n        {\n            \"code\": \"105331026051\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德新安江支行\"\n        },\n        {\n            \"code\": \"105331026060\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德新广场支行\"\n        },\n        {\n            \"code\": \"105331026078\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司建德大同支行\"\n        },\n        {\n            \"code\": \"105331027005\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行浙江省分行淳安支行\"\n        },\n        {\n            \"code\": \"105331027021\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司淳安城中支行\"\n        },\n        {\n            \"code\": \"105331027030\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司淳安湖光支行\"\n        },\n        {\n            \"code\": \"105331081157\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105331000000\",\n            \"name\": \"中国建设银行股份有限公司杭州转塘支行\"\n        },\n        {\n            \"code\": \"105331088888\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105100000017\",\n            \"name\": \"中国建设银行股份有限公司浙江省分行营业部\"\n        },\n        {\n            \"code\": \"105331099997\",\n            \"bankCode\": \"105\",\n            \"cityCode\": \"3301\",\n            \"pbcNo\": \"105331000000\",\n            \"name\": \"中国建设银行浙江省分行营业部运行中心\"\n        }\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  },
  {
    "type": "get",
    "url": "v3/card/bankCity",
    "title": "获取银行城市信息接口",
    "version": "0.1.0",
    "name": "bankCity",
    "group": "card",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "provinceCode",
            "description": "<p>省份编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n    \"success\": true,\n    \"message\": \"成功\",\n    \"data\": {\n        \"province\": [\n            {\n                \"code\": \"11\",\n                \"name\": \"北京市\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"12\",\n                \"name\": \"天津市\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"13\",\n                \"name\": \"河北省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"14\",\n                \"name\": \"山西省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"15\",\n                \"name\": \"内蒙古自治区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"21\",\n                \"name\": \"辽宁省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"22\",\n                \"name\": \"吉林省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"23\",\n                \"name\": \"黑龙江省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"31\",\n                \"name\": \"上海市\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"32\",\n                \"name\": \"江苏省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"33\",\n                \"name\": \"浙江省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"34\",\n                \"name\": \"安徽省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"35\",\n                \"name\": \"福建省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"36\",\n                \"name\": \"江西省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"37\",\n                \"name\": \"山东省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"41\",\n                \"name\": \"河南省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"42\",\n                \"name\": \"湖北省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"43\",\n                \"name\": \"湖南省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"44\",\n                \"name\": \"广东省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"45\",\n                \"name\": \"广西壮族自治区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"46\",\n                \"name\": \"海南省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"50\",\n                \"name\": \"重庆市\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"51\",\n                \"name\": \"四川省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"52\",\n                \"name\": \"贵州省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"53\",\n                \"name\": \"云南省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"54\",\n                \"name\": \"西藏自治区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"61\",\n                \"name\": \"陕西省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"62\",\n                \"name\": \"甘肃省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"63\",\n                \"name\": \"青海省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"64\",\n                \"name\": \"宁夏回族自治区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"65\",\n                \"name\": \"新疆维吾尔自治区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"71\",\n                \"name\": \"台湾省\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"81\",\n                \"name\": \"香港特别行政区\",\n                \"status\": \"0\"\n            },\n            {\n                \"code\": \"82\",\n                \"name\": \"澳门特别行政区\",\n                \"status\": \"0\"\n            }\n        ],\n        \"city\": [\n            {\n                \"code\": \"3301\",\n                \"provinceCode\": \"33\",\n                \"name\": \"杭州市\",\n                \"isHot\": 1,\n                \"searchKey\": \"hangzhou\"\n            },\n            {\n                \"code\": \"3302\",\n                \"provinceCode\": \"33\",\n                \"name\": \"宁波市\",\n                \"isHot\": 0,\n                \"searchKey\": \"ningbo\"\n            },\n            {\n                \"code\": \"3303\",\n                \"provinceCode\": \"33\",\n                \"name\": \"温州市\",\n                \"isHot\": 0,\n                \"searchKey\": \"wenzhou\"\n            },\n            {\n                \"code\": \"3304\",\n                \"provinceCode\": \"33\",\n                \"name\": \"嘉兴市\",\n                \"isHot\": 0,\n                \"searchKey\": \"jiaxing\"\n            },\n            {\n                \"code\": \"3305\",\n                \"provinceCode\": \"33\",\n                \"name\": \"湖州市\",\n                \"isHot\": 0,\n                \"searchKey\": \"huzhou\"\n            },\n            {\n                \"code\": \"3306\",\n                \"provinceCode\": \"33\",\n                \"name\": \"绍兴市\",\n                \"isHot\": 0,\n                \"searchKey\": \"shaoxing\"\n            },\n            {\n                \"code\": \"3307\",\n                \"provinceCode\": \"33\",\n                \"name\": \"金华市\",\n                \"isHot\": 0,\n                \"searchKey\": \"jinhua\"\n            },\n            {\n                \"code\": \"3308\",\n                \"provinceCode\": \"33\",\n                \"name\": \"衢州市\",\n                \"isHot\": 0,\n                \"searchKey\": \"quzhou\"\n            },\n            {\n                \"code\": \"3309\",\n                \"provinceCode\": \"33\",\n                \"name\": \"舟山市\",\n                \"isHot\": 0,\n                \"searchKey\": \"zhoushan\"\n            },\n            {\n                \"code\": \"3310\",\n                \"provinceCode\": \"33\",\n                \"name\": \"台州市\",\n                \"isHot\": 0,\n                \"searchKey\": \"taizhou\"\n            },\n            {\n                \"code\": \"3311\",\n                \"provinceCode\": \"33\",\n                \"name\": \"丽水市\",\n                \"isHot\": 0,\n                \"searchKey\": \"lishui\"\n            }\n        ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  },
  {
    "type": "get",
    "url": "v3/card/banks",
    "title": "获取支持银行信息",
    "version": "0.1.0",
    "name": "banks",
    "group": "card",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n    \"success\": true,\n    \"message\": \"成功\",\n    \"data\": [\n        {\n            \"id\": 125,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"105\",\n            \"codeName\": \"建设银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 126,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"103\",\n            \"codeName\": \"农业银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 127,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"104\",\n            \"codeName\": \"中国银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 128,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"303\",\n            \"codeName\": \"光大银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 129,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"310\",\n            \"codeName\": \"浦发银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 130,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"102\",\n            \"codeName\": \"工商银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 131,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"301\",\n            \"codeName\": \"交通银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 132,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"305\",\n            \"codeName\": \"民生银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 133,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"306\",\n            \"codeName\": \"广发银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        },\n        {\n            \"id\": 134,\n            \"codeKey\": \"bank_branch\",\n            \"codeNo\": \"307\",\n            \"codeName\": \"平安银行\",\n            \"langType\": \"zh_CN\",\n            \"sortNo\": 0,\n            \"ver\": 1\n        }\n    ]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  },
  {
    "type": "post",
    "url": "v3/card/bindConfirm",
    "title": "绑卡确认",
    "version": "0.1.0",
    "name": "bindConfirm",
    "group": "card",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>银行卡ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"MOBILE\""
            ],
            "optional": false,
            "field": "mediaType",
            "description": "<p>绑卡类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mediaId",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cardId",
            "description": "<p>卡号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityCode",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cardHolder",
            "description": "<p>持卡人姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankBranch",
            "description": "<p>支行</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bindNo",
            "description": "<p>绑定单号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "verifyCode",
            "description": "<p>短信验证码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Example:",
          "content": "\nbankBranch = \"中国建设银行总行(不受理个人业务)\";\ncardHolder = \"张冰冰\";\ncardId = 123665589666444;\ncity = \"北京市\";\nidentityCode = 412825199202026752;\nmediaId = 18458891234;\nmobile = 18458891231;\nprovince = \"北京市\";\nbindNo = \"1111111111111111111\";\nverifyCode = \"123456\";",
          "type": "json"
        }
      ]
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": [{\"error\":null,\"message\":\"success\"}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  },
  {
    "type": "get",
    "url": "v3/card/isBindCard",
    "title": "是否绑卡接口",
    "version": "0.1.0",
    "name": "isBindCard",
    "group": "card",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": {\"isBindCard\":true}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  },
  {
    "type": "post",
    "url": "v3/card/toBind",
    "title": "绑卡申请",
    "version": "0.1.0",
    "name": "toBind",
    "group": "card",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"MOBILE\""
            ],
            "optional": false,
            "field": "mediaType",
            "description": "<p>绑卡类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mediaId",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cardId",
            "description": "<p>卡号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identityCode",
            "description": "<p>身份证号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "cardHolder",
            "description": "<p>持卡人姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "bankBranch",
            "description": "<p>支行</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Example:",
          "content": "\nbankBranch = \"中国建设银行总行(不受理个人业务)\";\ncardHolder = \"张冰冰\";\ncardId = 123665589666444;\ncity = \"北京市\";\nidentityCode = 412825199202026752;\nmediaId = 18458891234;\nmobile = 18458891231;\nprovince = \"北京市\";",
          "type": "json"
        }
      ]
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>认证头信息: Bearer {access_token}</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Head Example:",
          "content": "Authorization: Bearer 4917172b-685e-4cdf-b518-21bb118018b5",
          "type": "String"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "success",
            "description": "<p>正确错误</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>消息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userBankCardId",
            "description": "<p>用户绑卡ID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "bindNo",
            "description": "<p>绑卡申请号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Example:",
          "content": "{\n  \"success\": true,\n  \"message\": \"\",\n  \"data\": [{\"result\":true,\"userBankCardId\":\"515\",\"bindNo\":\"123123123132\"}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "E:/project/trj-jk-web/src/main/webapp/api/card.js",
    "groupTitle": "card"
  }
] });
