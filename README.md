# EXP
主要用到OkHttp，GreenDao，IntentService。
抽象OkHttp使每个请求实现越来越简单，抽象DataBaseManager适用于所有数据库的增删改查，而且加了事物，插入集合的话就用实物就好了，官方说会比直接插入集合快一千倍。
用EventBus向前台通知结果，为了区分每个接口的失败通知，子类需实现getPostEvent（）方法，return子类对象，UI层用该对象接收即可。
抽象的BaseRecyclerViewAdapter加入itemClick，itemLongClick，子类实现更简单易懂。
说多无意，直接看源码，第一次分享，多多提问题，共同解决。
