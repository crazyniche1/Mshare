package com.crazy.mshare.concreteBuilder

import android.app.Activity
import com.crazy.mshare.builder.ShareBuilder
import com.crazy.mshare.product.SharePd

/**
 * Copyright (C), 2015-2021, 博彦科技
 * FileName: ConcreteShareBuilder
 * Author: zy
 * Date: 2021/11/8 18:21
 * Description: 创建具体的建造者
 * History:

 */
class ConcreteShareBuilder(activity: Activity):ShareBuilder() {

    //实例化dd
    private val spd = SharePd(activity)

    override fun buildShareText(content: String) {
        spd.shareText(content)
    }

    override fun buildShareImage(uri: String) {
        spd.shareImage(uri)
    }

    override fun buildShareImages(imageList : List<String>) {
        spd.shareImages(imageList)
    }

    override fun buildShareEmail() {
        spd.shareEmail()
    }

    override fun create(): SharePd {
        return  spd
    }

}