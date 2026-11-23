# JSON 格式统一

> `problem.json` / `solution.json` 的字段约定。
> 全队按这一份来，不要自己另起名字。

---

## 一、problem.json（输入：这道题问什么）

单位约定：时间 = 分钟（0:00 起），距离 = 格（格距 12）
方向约定：`edges` 是无向边，`0→1` 和 `1→0` 都通、距离相同

```jsonc
{
  // 全局设置：一次算例里所有车共用的参数
  "settings": {
    "serviceTime": 2,      // 每停一站（取件或送件）花几分钟，不计入行驶时间
    "batteryRange": 800    // 满电能跑多少个距离单位。电量 100% = 800
                           // 判断"电够不够"：剩余电量% × 800 ≥ 还要跑的距离
  },

  // 路网：车能在哪里跑
  "network": {
    "nodes": [
      // id = 节点编号；x/y = 平面坐标，x 向右、y 向上
      // 节点类型这一版不分（路口/楼门/充电桩都一样），充电桩在 stations 里单独标
      { "id": 0, "x": 0,  "y": 0 },
      { "id": 1, "x": 12, "y": 0 }
    ],
    "edges": [
      // from/to = 两端节点；dist = 距离；time = 开车要几分钟
      // 无向：只写一条，两个方向都能走
      { "from": 0, "to": 1, "dist": 12, "time": 10 }
    ]
  },

  // 车辆：谁能干活
  "vehicles": [
    {
      "id": 1,           // 车辆编号
      "startNode": 0,    // 0:00 时停在哪（出发点）
      "capacity": 20,    // 最多能装多重（和 task.weight 单位一致）
      "battery": 100     // 出发时电量百分比，100 = 满电
    }
  ],

  // 订单：要干什么活
  "tasks": [
    {
      "id": 1,           // 订单编号
      "pickup": 12,      // 取件节点 id
      "delivery": 30,    // 送件节点 id
      "weight": 3,       // 重量，占用车的 capacity
      "due": 720         // 最晚送达时间（分钟）。720 = 12:00
                         // 注意：due 太小会导致大量订单不可行
    }
  ],

  // 充电桩：位置。这一版算法先忽略，但前端要画
  "stations": [
    { "id": 1, "node": 12 }
  ]
}
{
  "feasible": true,        // 有没有解。false 时 routes 为空，metrics 里说明原因
  "routes": [
    {
      "vehicleId": 1,      // 这条路线属于哪台车
      "stops": [
        {
          "seq": 1,           // 第几站，从 1 开始，必须连续
          "node": 12,         // 停在哪个节点
          "action": "pickup", // pickup=取件 / delivery=送件 / charge=充电（预留）
          "taskId": 1,        // 对应哪个订单（charge 时为 null）
          "arrive": 512       // 计划到达时间（分钟）。512 = 8:32
                              // 这里填的是"到达时刻"，不是"停了多久"
        },
        {
          "seq": 2,
          "node": 30,
          "action": "delivery",
          "taskId": 1,
          "arrive": 560
        }
      ],
      "totalDistance": 84   // 这台车总共跑了多少
    }
  ],
  "metrics": {
    "totalDistance": 420,   // 所有车加起来
    "lateCount": 2,         // 超过 due 才送达的订单数
    "solveTimeMs": 180      // 算这一题花了多少毫秒
  }
}
//这个和群里的一样，大家参考这个开发！！！！