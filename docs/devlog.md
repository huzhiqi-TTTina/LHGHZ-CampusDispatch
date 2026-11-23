# 胡202611232155

- 路径：`D:\projects\LHGHZ-CampusDispatch`
- 包：`com.campus.core.tools` 地图工具生成代码

---

## 大类的主要目的

生成地图。

## 两个 import

- 首先我们运用了 `import java.io.PrintWriter;`
- `import java.nio.charset.StandardCharsets;`
- 两个 import 主要目的是为了让文中的
  `try (PrintWriter w = new PrintWriter("roadnet.json", StandardCharsets.UTF_8))` 更简洁
- 这句话的意思是在 `roadnet.json` 里（就是定义当前的名字）用 `StandardCharsets.UTF_8` 写

## 写死的常量

- 我们在这个版本里写死了列的个数为 10 个，行的个数为 5 个
- 然后距离均为 12（但是之后这个距离可能会改）
- 然后小车的速度为 1.2

## private static final

`private static final` 的意思就是静态的、只在这个文件里面使用的最终量（就是不会也不可以改变）。

## 整个类

整个类有一个函数，就是这个地图生成器。

## 类的入口

- 紧接着进入类，`public static void main(String[] args) throws Exception`
- 这是一个新的类的入口，需要 `public`，然后 `static` 这两个，因为类的入口只识别这
- 然后 `String[] args` 这个是从输入来传入参数，但是目前没有，不过需要保留
- `throws Exception` 是如果 `try` 出现问题要抓住错误甩给调用者

## 点的输出

- `\"` 是 `"` 的转义符
- 然后首先进入点的输出
- 首先从第一个点开始，定死这一行开始把第一行元素遍历，然后逐层遍历
- `id` 的设置就是行乘 10 再加上列，也就是说（1，2）的 `id` 就是 12
- 然后输出即可

## 最后一个元素的判断

- 最后需要判断这个元素是不是最后一个元素
- 判断 `id` 和 `COLS * ROWS - 1` 是否相等（本轮等于 49）
- 只有最后一个位置（也就是 `id = 4*10 + 9`）才会得到肯定的结果，不输出 `，`

## 边的输出

- 接着进入边的输出
- 开始需要定义一个 `first`，开始定义为真
- 然后同样开始遍历，每次计算 `id` 和时间（采用距离除以速度，目前距离一样）
- 然后每次输出当前节点和右边结点的路
- 然后输出当前结点和下面结点的路

## first 的作用

- 如果是 `first`，也就是说是第一边，那就不输出 `，`
- 如果不是第一边，也就打逗号
- （还没换行的时候先进行下一次操作，然后打逗号，也就是这一行的末尾，然后换行，`println` 是换行后输出）

## 收尾

然后换行后输出 `]` 和 `}` 即可（这里写死了充电站）
## 写上传了json格式统一的相关内容，大家查看后再开始