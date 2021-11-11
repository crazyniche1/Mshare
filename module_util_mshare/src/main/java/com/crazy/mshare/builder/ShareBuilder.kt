package com.crazy.mshare.builder

import android.net.Uri
import com.crazy.mshare.product.SharePd

/**
 * Copyright (C), 2015-2021, 博彦科技
 * FileName: ShareBuilder
 * Author: zy
 * Date: 2021/11/8 18:17
 * Description:
 * History:

 */
abstract  class ShareBuilder {

    abstract fun buildShareText (content: String)
    abstract fun buildShareImage (uri: String)
    abstract fun buildShareImages (imageList : List<String>)
    abstract fun buildShareEmail ()

    abstract fun create ():SharePd

}