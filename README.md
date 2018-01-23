<p align="center"><img width="400" src="http://s1.wailian.download/2017/10/27/LOGO.png"></p>

<p align="center">
  <a href="#"><img src="https://img.shields.io/badge/devTool-IDEA-yellow.svg" alt=""></a>
  <a href="#"><img src="https://travis-ci.org/Alamofire/Alamofire.svg?branch=master" alt=""></a>
  <a href="#"><img src="https://img.shields.io/packagist/l/doctrine/orm.svg" alt="LICENSE"></a>
  <a href="#"><img src="https://img.shields.io/badge/platform-OSX%7CWin%7CLinux-blue.svg" alt=""></a>
  <a href="#"><img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=103" alt=""></a>   	
  <a href="#"><img src="https://img.shields.io/badge/language-java-blue.svg" alt=""></a>  
</p>


任务列表：

- [x] 项目搭建
- [x] 前后台参数传递约定
- [x] 统一响应结构
- [x] 统一异常处理
- [x] CORS跨域请求处理
- [x] JWT鉴权机制，保证接口安全
- [x] 集成Swagger2，自动生成restful api文档
- [x] 集成通用Mapper、pagehelper分页插件
- [x] Hibernate Vilation参数校验机制

<p align="center">
    <img src="http://og1m51u2s.bkt.clouddn.com/20171111151040758247575.png" alt="">
</p>

# 前言

首先在此之前，我跟大多数人一样，心中有如下的疑问？

1. 什么是前后端分离？
2. 前后端分离的意义大不大？
3. 如何进行前后端分离？

那么文章将围绕这三个疑问进行展开，当然文章的重点还是总结如何进行前后端分离。

# 什么是前后端分离？

前后端分离：就是前后端只通过JSON进行交流，前端通过ajax请求后台，后台返回json格式的数据（当然json只是一种可选的格式，并不是唯一的）。前端可以通过Vue、Angular实现组件化，降低前后端的耦合程度。

# 前后端分离的意义大不大？

1. 如果系统的业务比较复杂，网站前端变化远比后端变化频繁，则意义大。
2. 该网站尚处于原始开发模式，数据逻辑与表现逻辑混杂不清，则意义大。
4. 该网站要适配多平台，需要对设备的兼容性有要求，则意义大。
5. 该网站将业务拆分成微服务，则意义大。

# 如何进行前后端分离？

那如何进行前后端分离，这里我只针对后台来讨论，因为现在主要负责后台的开发，那至于说前端如何请求数据，前端数据的缓存等等这个就不在这里讨论了。要想实现前后端解耦，后端必须遵守RESTful API的设计准则。RESTful API 是目前比较成熟的一套互联网应用程序的 API 设计理论，至于具体什么是 RESTful API，可以参考阮一峰老师的博文：[RESTful API 设计指南](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)，便会对RESTful API 有个大概的了解。那要搭建一个RESTful API的后台项目具体需要考虑哪些东西呢？我根据我自己的实际开发，总结了如下几个点：

1. **统一响应结构。**
2. **前台请求规范。**
3. **API接口文档。**
4. **统一异常处理。**
5. **后台参数验证。**
6. **跨域请求处理。**
7. **请求鉴权机制。**

接下来将逐一的对每个点进行总结。

# 统一响应结构

我们在开发之前，需要跟前后端约定好，每次ajax请求，后端都需要返回一个统一的数据格式。如果格式不统一，前端请求每次拿到的数据很乱，如果前端页面变化的比较频繁，那么后期维护的成本很大。下面就是一个json格式的响应结构：

```java
{
    data : { // 请求数据，对象或数组均可
        user_id: 123,
        user_name: "tutuge",
        user_avatar_url: "http://tutuge.me/avatar.jpg"
        ...
    },
    msg : "请求成功！", // 请求状态描述，调试用
    code: 500, // 业务自定义状态码，比如500表示请求失败，200表示请求成功
    extra : { // 全局附加数据，字段、内容不定，可能为null
        type: 1,
        desc: "签到成功！"
    }
}
```

对应的java的实体类`ResultBean.java`：

```java
public class ResultBean {
    /**
     * 数据集
     */
    private Object data = null;
    /**
     * 返回信息
     */
    private String msg = "Request Success！";
    /**
     * 业务自定义状态码
     */
    private Integer code = 200;
    /**
     * 全局附加数据
     */
    private Object etxra = null;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getEtxra() {
        return etxra;
    }

    public void setEtxra(Object etxra) {
        this.etxra = etxra;
    }
}
```

# 前台请求规范

后台的响应结构已经确定好，那么前端的请求是不是得规范一下呢，答案肯定是的！因为我们采用的是RESTful API设计原则，我们会严格按照约定来使用 HTTP method：

全部采用 **RESTful API** 风格，在采用RESTful的时候，我们会严格按照约定来使用 HTTP method：

- GET: 查询
    - 若查询参数在3个以下（包含3个），采用如下的请求方式：`http://localhost:8080/app/getUserList?age=12&name=Jack&sex=1`，将参数拼接到url后面，后台采用@RequestParam注解接收。
    - 若查询参数在三个以上，这里就采用POST请求，后台用@RequestBody注解将参数自动解析成查询的实体实例。
- POST: 创建
    - 请求参数类型为body，也就是json对象，将对应的参数封装成一个类，然后后台使用@RequestBody注解将参数自动解析成该类的一个实例。
- PUT: 修改
    - 第一个主键参数，他的请求url为：`http://localhost:8080/app/updateUser/{userId}`，采用@PathVariable注解接收。
    - 第二个请求参数，类型为body，json对象，跟POST创建请求一样，只是该json对象只放修改的属性内容，采用@RequestBody注解接收。
- DELETE: 删除
    - 请求url：`http://localhost:8080/app/deleteUser/{userId}`
    - 后台采用@PathVariable注解接收参数。

其实关于GET查询这里，如果查询参数在三个以上的话，并不说一定要采用GET请求。我思考了下，这里用POST请求的方式更加合理，post请求参数类型可以为body，也就是用json对象。后台用@RequestBody注解将参数自动解析成查询的实体实例。虽然说不符合RESTful API设计原则，但是我觉得这里的**业务合理性大于设计准则**，因为像京东商城，他们的复杂的查询都是采用都是POST的请求方式，所以到底采用哪种方式，还是得根据业务需求来。

# API接口文档

写后台的同学有没有这样的烦劳，每次写完相关的接口，都要写相关的接口文档，然后跟前端小伙伴进行联调，过程很是繁琐和费时间。那为了解决这些问题，Swagger2 就是一个很好的解决方案，它与 spring mvc 整合后，我们只需要少量的注解，它便可以自动的帮我们生成一份 RESTful API 文档，大大的减轻了劳动力。因为之前有写过一篇关于这个问题文章：[Spring MVC中使用Swagger2构建Restful API](http://leeyom.top/2017/09/23/tech-spring-mvc-swagger2/)，这里就不在重复叙述了。

# 统一异常处理

采用spring的AOP（面向切面编程），编写一个全局的异常处理切面类，统一处理所有的异常。定义一个类，然后用`@ControllerAdvice`注解将其标注即可，同时用`@ResponseBody`注解表示返回值可序列化为JSON字符串。代码如下(`ExceptionAspect.java`)：

```java
/**
 * 全局异常处理切面
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAspect {
    private static final Logger log = Logger.getLogger(ExceptionAspect.class);
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("Could not read json...");
        log.error("Could not read json...", e);
        return resultBean;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultBean handleValidationException(MethodArgumentNotValidException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("参数检验异常！");
        log.error("参数检验异常！", e);
        return resultBean;
    }

    /**
     * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultBean handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(405);
        resultBean.setMsg("请求方法不支持！");
        log.error("请求方法不支持！", e);
        return resultBean;
    }

    /**
     * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
     * 是ServletException的子类,需要Servlet API支持
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResultBean handleHttpMediaTypeNotSupportedException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(415);
        resultBean.setMsg("内容类型不支持！");
        log.error("内容类型不支持！", e);
        return resultBean;
    }

    /**
     * 401 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenException.class)
    public ResultBean handleTokenException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(401);
        resultBean.setMsg("Token已失效");
        log.error("Token已失效", e);
        return resultBean;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(500);
        resultBean.setMsg("内部服务器错误！");
        log.error("内部服务器错误！", e);
        return resultBean;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultBean handleValidationException(ValidationException e) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(400);
        resultBean.setMsg("参数验证失败！");
        log.error("参数验证失败！", e);
        return resultBean;
    }
}
```

为了能让`@ControllerAdvice`注解生效，还需要在spring MVC的配置文件：`spring-mvc.xml`添加如下一句：

```xml
<context:component-scan base-package="com.artisan.*" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    <!-- 控制器增强，使一个Contoller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常 -->
    <context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
</context:component-scan>
```
这样就完成了全局的异常处理，一旦后台出现异常，就返回给前台指定的异常的JSON数据。前台开发人员看到此异常后，就应该立即反馈给后台开发人员。

# 后台参数验证

前台在请求之前也会进行参数验证，但是为了程序更加严谨，后台也需要进行参数验证，这样做的好处就是，可以防止`脏数据`的出现，过滤掉一些不符合要求的请求。打个比方吧，就比如新增一个用户，`username`和`password`都是必填字段，假如说前端没有做判断，这个时候用户点击保存，后台没做参数验证，就将这个脏数据保存进数据库。

这里我们将采用`Hibernate Validator`框架去实现后台的参数校验。别看到这里有`hibernate`这个单词，其实跟`hibernate`这个orm框架一毛钱关系都没有，他们之间是没有任何的依赖关系的。在`pom.xml`中添加如下依赖：

```xml
<!--Hibernate Validator-->
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>6.0.4.Final</version>
</dependency>
```

在spring的配置文件`applicationConext.xml`中装配参数验证器：

```xml
<!--Hibernate Validator-->
<bean class="org.springframework.validation.beanvalidation.MethodValidationPostProcessor"/>
```

在对应的controller的请求方法中，对需要验证的请求参数用`@Valid`进行标注，表示这个实体类的有些属性是需要进行参数验证的。

```java
@ApiOperation(value = "新增User")
@ResponseBody
@RequestMapping(value = "/", method = RequestMethod.POST)
public ResultBean add(@ApiParam(value = "新增User实体", required = true) @RequestBody @Valid User user) {
   ResultBean resultBean = new ResultBean();
   try {
       userService.insert(user);
   } catch (Exception e) {
       resultBean.setCode(StatusCode.HTTP_FAILURE);
       resultBean.setMsg("Create User Failed！");
       LOGGER.error("新增User！参数信息：User = " + user.toString(), e);
   }
   return resultBean;
}
```

对应的`User.java`实体类中，需要使用`@NotEmpty`标注某个属性是不能为空：

```java
public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @NotEmpty
    private String userName;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;
    
}
```
那当然是不止`@NotEmpty`这一个检验注解，`Hibernate Validator`框架给我们提供了丰富的校验注解，常用的如下：

- Bean Validation 中内置的 constraint：
    - **@Null**：被注释的元素必须为 null
    - **@NotNull**：被注释的元素必须不为 null
    - **@AssertTrue**：被注释的元素必须为 true
    - **@AssertFalse**：被注释的元素必须为 false
    - **@Min(value)**：被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    - **@Max(value)**：被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    - **@DecimalMin(value)**：被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    - **@DecimalMax(value)**：被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    - **@Size(max, min)**：被注释的元素的大小必须在指定的范围内
    - **@Digits (integer, fraction)**：被注释的元素必须是一个数字，其值必须在可接受的范围内
    - **@Past**：被注释的元素必须是一个过去的日期
    - **@Future**：被注释的元素必须是一个将来的日期
    - **@Pattern(value)**：被注释的元素必须符合指定的正则表达式
- Hibernate Validator 附加的 constraint：
    - **@Email**：被注释的元素必须是电子邮箱地址
    - **@Length**：被注释的字符串的大小必须在指定的范围内
    - **@NotEmpty**：被注释的字符串的必须非空
    - **@Range**：被注释的元素必须在合适的范围内

这样我们的项目就集成了`Bean Validation`特性，就可以使用这些注解要进行参数校验了。

# 跨域请求处理

前端是纯静态的页面，通过ajax请求后台，但是我们知道，ajax存在一个问题就是不支持跨域访问的。也就是说，前后端两个应用必须在同一个域名下才能访问。那该怎么样才能解决这个问题呢？这里采用的是CORS（Cross Origin Resource Sharing）方案，翻译过来就是：**跨域资源共享**。CORS技术很简单，现在大多数的浏览器都已经支持了，只需后台将CORS相应头写入response对象中即可。

那后台就需要编写一个过滤器：`CorsFilter.java`，拦截所有的http请求，然后将CORS响应头写入到response对象中即可，代码如下：

```java
/**
 * 处理跨域的过滤器
 * @author Leeyom Wang
 * @date 2017年10月19日 14:47
 */
@Component
public class CorsFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(CorsFilter.class);

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    /**
     * 通过CORS技术实现AJAX跨域访问, 只要将CORS响应头写入response对象中即可
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        if (StringUtil.isNotEmpty(allowOrigin)) {
            //允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制；
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            //允许访问的请求方式，多个用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS；
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            //是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true；
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type,Access-Token,timestamp
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            //允许客户端访问的服务端响应头，多个响应头用逗号分割。
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
```

`web.xml`中配置CorsFilter过滤器：

```xml
<!-- 通过CORS技术实现AJAX跨域访问 -->
<filter>
   <filter-name>corsFilter</filter-name>
   <filter-class>com.artisan.common.filter.CorsFilter</filter-class>
   <init-param>
       <param-name>allowOrigin</param-name>
       <param-value>*</param-value>
   </init-param>
   <init-param>
       <param-name>allowMethods</param-name>
       <param-value>GET,POST,PUT,DELETE,OPTIONS</param-value>
   </init-param>
   <init-param>
       <param-name>allowCredentials</param-name>
       <param-value>true</param-value>
   </init-param>
   <init-param>
       <param-name>allowHeaders</param-name>
       <param-value>Content-Type,Access-Token,timestamp</param-value>
   </init-param>
</filter>
```

这样我们就解决了跨域的问题。

# 请求鉴权机制

由于http请求是无状态的，我们后端写好了API接口，然后发布出去，如果不做安全控制，谁都可以调用，这很明显是非常不安全的，所以我们需要采用JWT（Json web token）鉴权机制去保护我们的API接口安全，整个的思路如下：

1. 用户登陆后，服务器端使用 jjwt(当然也可以采用其他的方式，比如时间戳，签名url) 生成 Token ，保存在 Redis 中，以用户名作为 Key，同时将此token值返回给前端。
2. 通过设置 Redis 键的 TTL 来实现 Token 自动过期。
3. 前端将token值存到localStorage中，后面每次请求，都将次token放到header（请求头）中。
4. 服务端通过在 Filter 中拦截请求判断 Token 是否有效，如果有效，则请求通过，无效，返回401，提示无此权限。
5. 由于 Redis 是基于 Key-Value 进行存储，因此可以实现新的 Token 将覆盖旧的 Token ，保证一个用户在一个时间段只有一个可用 Token，但是如果有些系统允许当前用户可以多处登陆，则不需要处理这一步。
6. 从头至尾，整个过程没有涉及cookie，所以CSRF或者XXS等相关的攻击 是不可能发生的。

首先定义一个管理token的接口，`TokenManager.java`：

```java
/**
 * 对Token进行操作的接口
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    TokenModel createToken(long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    void deleteToken(long userId);

    /**
     * 保证一个用户在一个时间段只有一个可用 Token
     * @param userId
     * @return
     */
    boolean hasToken(long userId);
}
```

其对应的接口实现类`RedisTokenManager.java`，对token进行增删改查操作：

```java
/**
 * 通过Redis存储和验证token的实现类
 * @author leeyom
 * @date 2017年10月19日 10:41
 * @see com.artisan.authorization.manager.TokenManager
 */
@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<Long, String> redis;
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(long userId) {
        //uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //时间戳
        String timestamp = SDF.format(new Date());
        //token => userId_timestamp_uuid;
        String token = userId + "_" + timestamp + "_" + uuid;
        TokenModel model = new TokenModel(userId, uuid, timestamp);
        //存储到redis并设置过期时间(有效期为2个小时)
        redis.boundValueOps(userId).set(Base64Util.encodeData(token), Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 3) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String timestamp = param[1];
        String uuid = param[2];
        return new TokenModel(userId, uuid, timestamp);
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !(Base64Util.decodeData(token)).equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间(2个小时)
        redis.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public void deleteToken(long userId) {
        redis.delete(userId);
    }

    @Override
    public boolean hasToken(long userId) {
        String token = redis.boundValueOps(userId).get();
        if (StringUtils.notNull(token)) {
            return true;
        } else {
            return false;
        }
    }
}
```

利用spring的APO技术，编写一个切面类`SecurityAspect.java`，拦截所有Controller类的方法，并从请求头中获取token，最后对token有效性进行判断，代码如下：

```java
/**
 * token有效性判断切面
 * @author leeyom
 * @date 2017年10月19日 10:41
 */
@Component
@Aspect
public class SecurityAspect {

    private static final Logger LOGGER = Logger.getLogger(SecurityAspect.class);

    @Autowired
    TokenManager tokenManager;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        //SimpleDateFormat是线程不安全的，设置为static，多线程情况下会报java.lang.NumberFormatException: multiple points
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        LOGGER.debug("methodSignature : " + methodSignature);
        Method method = methodSignature.getMethod();
        LOGGER.debug("Method : " + method.getName() + " : " + method.isAnnotationPresent(IgnoreSecurity.class));

        //如果是获取Swagger API文档，直接调用目标方法，生产环境下需要注释掉
        String methodName = method.getName();
        if ("getDocumentation".equals(methodName)) {
            return pjp.proceed();
        }

        // 若目标方法忽略了安全性检查,则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }

        // 从 request header 中获取当前 token
        String authentication = WebContextUtil.getRequest().getHeader(Constants.DEFAULT_TOKEN_NAME);
        // 从 request header 中获取时间戳
        String timestampStr = WebContextUtil.getRequest().getHeader(Constants.TIME_STAMP);
        TokenModel tokenModel = tokenManager.getToken(Base64Util.decodeData(authentication));

        Date date = new Date();
        Date timestamp = sdf.parse(timestampStr);

        //验证时间戳是否超过五分钟，如果超过五分钟，则服务端删除此token，防止抓包
        if (((date.getTime()) - timestamp.getTime()) / 60000 > 5) {
            if (tokenModel != null) {
                //删除token
                tokenManager.deleteToken(tokenModel.getUserId());
            }
            throw new TokenException("请求超时~");
        }

        // 检查 token 有效性
        if (!tokenManager.checkToken(tokenModel)) {
            String message = "token " + Base64Util.decodeData(authentication) + " is invalid！！！";
            LOGGER.debug("message : " + message);
            throw new TokenException(message);
        }
        // 调用目标方法
        return pjp.proceed();
    }
}
```

若要使SecurityAspect生效，则需要在SpringMVC配置文件中添加如下Spring 配置：

```xml
<!-- 支持Controller的AOP代理 -->
<aop:aspectj-autoproxy />
```

最后还需要在`web.xml`中添加Access-Token,以及时间戳timestamp。

```xml
<init-param>
    <param-name>allowHeaders</param-name>
    <param-value>Content-Type,Access-Token,timestamp</param-value>
</init-param>
```

那为什么要请求头要带一个时间戳呢？其实是防止人家抓包：

```java
//验证时间戳是否超过五分钟，如果超过五分钟，则服务端删除此token，防止抓包
if (((date.getTime()) - timestamp.getTime()) / 60000 > 5) {
  if (tokenModel != null) {
      //删除token
      tokenManager.deleteToken(tokenModel.getUserId());
  }
  throw new TokenException("请求超时~");
}
```

ok，这样我们的后端的API接口就有安全保障了。

# 总结

以上便是我自己在实际开发中对于前后端分离的一些思考，可能有些地方考虑的不够周全，但是也算是一个基础的RESTful API接口平台了，文章相关的示例代码我已经整理成了一个基本的项目，托管在github，大家可以自由下载，github地址：[https://github.com/wangleeyom/code-artisan](https://github.com/wangleeyom/code-artisan)，如果对你有帮助的话，就点个star，有疑惑的地方，就在文章下面评论吧，大家一起讨论。

