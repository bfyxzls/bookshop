# bookshop
主要是一个微型电商平台，用户，商品，订单的管理。
# 技术点
1. springboot
2. mybatis
3. mysql & h2
4. event
5. mq
6. cache
7.

# event
事件机制来解耦复杂业务，围绕一个主题去订阅，一个主题可以有多个订阅方，而每个订阅方就是处理这个复杂业务的关注点，
它们即是一个独立的，从而也是一个整体。