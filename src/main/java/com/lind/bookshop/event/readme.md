# 事件通知
一般在本项目内容，定义一个通知的数据载体，它有描述这个主题的各种字段组成，在程序内部可以去发布这个主题，
而被订阅的监听者将收到这个消息，并进行同步或者异步的处理。
## 定义主题
主要是可以正确描述这个主题即可，字段越少越好。
## 发布主题
使用`ApplicationEventPublisher`发布相关的主题，它不会关注有多少订阅者，它只关注主题本身。
## 订阅和关注某些主题
它是一个bean，默认是同步执行，需要希望以异步方式执行需要在方法上添加`Async`注解，它不关注是谁发布的主题，而只关注自己要如何处理这个主题。