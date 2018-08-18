/**
 * @api {post} oauth/token 获取accessToken
 * @apiVersion 1.0.0
 * @apiName getAccessToken
 * @apiGroup OAuth2
 * 
 * @apiHeader {String} Authorization 认证头信息: 使用Basic认证，CFD会提供给调用方用户名，密码(client_id和client_secret)，比如: 用户名为aaa,密码为123，使用Base64加密伪代码为 base64("aaa:123")，结果为 YWFhOjEyMw==
 * @apiHeaderExample {String} Head Example:
 * Authorization: Basic YWFhOjEyMw==
 *
 * @apiParam {String="password"} grant_type 授权类型: password
 * @apiParam {String} username 对应手机号(授权前需要先使用提交基本信息接口/v3/apply/add 形成用户后才能使用)
 * @apiParam {String} password 密码（会给默认密码）
 *
 * @apiParamExample {String} Example: 
 * grant_type=password
 * username=手机号
 * password=*****
 *
 * @apiSuccess {String} access_token Access Token
 * @apiSuccess {String} token_type 目前为bearer
 * @apiSuccess {String} expires_in 过期剩余时间：单位为秒
 * @apiSuccess {String} scope 调用方被授权的域
 * 
 * @apiSuccessExample {json} Success Example:
 * {
 *   "access_token": "1d44bad5-2b9e-46e5-bfbe-80a8988b99b7",
 *   "token_type": "bearer",
 *   "expires_in": 793,
 *   "scope": "write read"
 * }
 * 
 * @apiError Unauthorized 认证失败，返回http状态码401
 */
function getAccessToken() { return; }