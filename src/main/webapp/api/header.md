1. 在使用 API 之前，你需要了解OAuth2.0。根据分配的client_id和client_secret，并使用标准的 OAuth 2 实现登录，获得 access_token 信息。<br/>
<ul>
<li>OAuth 认证路径 - /oauth/token</li>
<li>Response 说明 - 所有 Response 采用 JSON 格式返回，请求状态通过 HTTP Status 返回。</li>
</ul>

2. HTTP Status<br/>
<ul>
<li>200, 201 - 请求成功，或执行成功。</li>
<li>400 - 参数不符合 API 的要求、或者数据格式验证没有通过，请配合 Response Body 里面的 error 信息确定问题。</li>
<li>401 - 用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token。</li>
<li>403 - 当前用户对资源没有操作权限。</li>
<li>404 - 资源不存在。</li>
<li>500 - 服务器异常。</li>
</ul>

3. 对于服务接口，正常响应信息格式为：
<br/>
<pre>
{
  success: true,
  message: "",
  data: {}
}
</pre>
<br/>
异常响应信息格式为:
<br/>
<pre>
{
  success : false,
  message: "错误消息",
  data: null
}
</pre>

4. 接口认证：<br/>
服务接口使用OAuth2认证，调用接口必须加上 Http头
Authorization: Bearer [access_token]
access_token通过 /oauth/token 接口获取， access_token具有生命周期，默认7天，过期后需要重新获取