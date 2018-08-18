define({
  "name": "cfd-serve",
  "version": "1.0.0",
  "description": "CFD服务",
  "title": "CFD服务",
  "url": "https://testjkweb.tourongjia.com/",
  "header": {
    "title": "概述",
    "content": "<ol>\n<li>在使用 API 之前，你需要了解OAuth2.0。根据分配的client_id和client_secret，并使用标准的 OAuth 2 实现登录，获得 access_token 信息。<br/></li>\n</ol>\n<ul>\n<li>OAuth 认证路径 - /oauth/token</li>\n<li>Response 说明 - 所有 Response 采用 JSON 格式返回，请求状态通过 HTTP Status 返回。</li>\n</ul>\n<ol start=\"2\">\n<li>HTTP Status<br/></li>\n</ol>\n<ul>\n<li>200, 201 - 请求成功，或执行成功。</li>\n<li>400 - 参数不符合 API 的要求、或者数据格式验证没有通过，请配合 Response Body 里面的 error 信息确定问题。</li>\n<li>401 - 用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token。</li>\n<li>403 - 当前用户对资源没有操作权限。</li>\n<li>404 - 资源不存在。</li>\n<li>500 - 服务器异常。</li>\n</ul>\n<ol start=\"3\">\n<li>对于服务接口，正常响应信息格式为：\n<br/></li>\n</ol>\n<pre>\n{\n  success: true,\n  message: \"\",\n  data: {}\n}\n</pre>\n<br/>\n异常响应信息格式为:\n<br/>\n<pre>\n{\n  success : false,\n  message: \"错误消息\",\n  data: null\n}\n</pre>\n<ol start=\"4\">\n<li>接口认证：<br/>\n服务接口使用OAuth2认证，调用接口必须加上 Http头\nAuthorization: Bearer [access_token]\naccess_token通过 /oauth/token 接口获取， access_token具有生命周期，默认7天，过期后需要重新获取</li>\n</ol>\n"
  },
  "footer": {
    "title": "代号定义",
    "content": ""
  },
  "order": [
    "OAuth2",
    "getAccessToken",
    "isBlacklist",
    "applyAdd",
    "applyFinish",
    "getLimitAuditList",
    "tryDoRepayAmount",
    "signatureAccredit",
    "doSignature",
    "register",
    "idCardOcr",
    "identityAuthAndSave",
    "livingBodyAuthAndSave",
    "uploadImg"
  ],
  "template": {
    "forceLanguage": "zh_cn",
    "withCompare": true,
    "withGenerator": true
  },
  "sampleUrl": false,
  "defaultVersion": "0.0.0",
  "apidoc": "0.3.0",
  "generator": {
    "name": "apidoc",
    "time": "2018-05-04T06:59:21.621Z",
    "url": "http://apidocjs.com",
    "version": "0.17.6"
  }
});
