package com.crazy.mshare.director

import com.crazy.mshare.builder.ShareBuilder

/**
 * Copyright (C), 2015-2021, 博彦科技
 * FileName: ShareDirector
 * Author: zy
 * Date: 2021/11/8 18:25
 * Description: 委托构建
 * History:

 */
class ShareDirector constructor (private var mShareBuilder :ShareBuilder?){

    fun construct (str:String) {
        mShareBuilder?.buildShareText(str)
    }

}