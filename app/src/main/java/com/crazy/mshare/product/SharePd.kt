package com.crazy.mshare.product

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.LabeledIntent
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.util.Log
import java.lang.NullPointerException

/**
 * Copyright (C), 2015-2021, 博彦科技
 * FileName: SharePd
 * Author: zy
 * Date: 2021/11/8 18:12
 * Description: 产品实体类
 * History:

 */
class SharePd constructor(private val activity : Activity) {

    private val targetIntents= arrayListOf<Intent>()
    private val noAppDesc = "没有可以分享的应用"
    private val title = "选择分享应用"
    private val mIntent =  Intent()
    private var mi : Intent? = null

    enum class ShareType(var string: String) {
        TEXT("text/plain"),
        IMAGE("image/*"),
    }
    init {
        //针对android 7 分享图片读取文件时会引发崩溃。7.0适配-应用之间共享文件
        if (Build.VERSION.SDK_INT >= 24) {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
    }

    val Tag = "SharePd"

    private fun shareIntent(type:String) :Intent {
        mIntent.action = Intent.ACTION_SEND
        mIntent.type = type
        //发送邮件添加的主题
//        mIntent.putExtra(Intent.EXTRA_SUBJECT,"Intent.EXTRA_SUBJECT")
//        LogTag.d("shareText_type: ${ShareType.TEXT.string}")
        return mIntent
    }

    /**
     * 设置文本分享
     */

    fun shareText (content: String) :SharePd{
         mi =  shareIntent(ShareType.TEXT.string)
         mi?.putExtra(Intent.EXTRA_TEXT,content)
        Log.d(Tag,content)
        return this
    }

    /**
     * 设置图片分享
     */

    fun shareImage (mUri: String) :SharePd{

        if(mUri.isNullOrEmpty()){
            NullPointerException("Image is null or empty ")
        }
        val uri = Uri.parse(String.format("file://%s",mUri))
        mi =  shareIntent(ShareType.IMAGE.string)
        mi?.putExtra(Intent.EXTRA_STREAM, uri)

        return this
    }

    /**
     * 设置多图片分享
     */

    fun shareImages (imageList : List<String>) :SharePd{
        if (imageList.isNullOrEmpty()){
            NullPointerException("ImageList is null or empty ")
        }
        val list =  arrayListOf<Uri>()
        for (e in imageList.withIndex()){
            val uri = Uri.parse(String.format("file://%s",e))
            list.add(uri)
        }
        mi =  shareIntent(ShareType.IMAGE.string)
        mi?.putParcelableArrayListExtra(Intent.EXTRA_STREAM,list)
        return this
    }

    /**
     * 设置邮件分享
     */
    fun shareEmail () :SharePd {

        return this
    }

    /**
     * 分享应用的过滤
     * 根据不同的业务需求 去 过滤筛选
     */

    fun imageAPP(mUri: String){
        if (mUri.isNullOrEmpty()){
            NullPointerException("ImageList is null or empty ")
        }
        val uri = Uri.parse(String.format("file://%s",mUri))
        val mi =  shareIntent(ShareType.IMAGE.string)
        mi.putExtra(Intent.EXTRA_STREAM, uri)
        mi.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        mi.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        getImageApps(mi,uri)


        val cci = Intent.createChooser(targetIntents.removeAt(targetIntents.size - 1), title)
        val labeledIntents: Array<Intent> = targetIntents.toTypedArray()
        if (cci==null){
            Log.d(Tag,noAppDesc)
            return
        }
        cci.putExtra(Intent.EXTRA_INITIAL_INTENTS,labeledIntents)
        activity.startActivity(cci)
    }

    //获取匹配图片类型的应用信息列表：
    private fun getImageApps(im:Intent, uri:Uri){
        val resInfo  = activity.packageManager.queryIntentActivities(im,0)
        if(resInfo.isEmpty()){
            Log.d(Tag,noAppDesc)
            return
        }

        for ((i,e) in  resInfo.withIndex()){
            val apm = activity.packageManager;
            val icon = e.loadIcon(apm)
            val label = e.loadLabel(apm)
            val ai = e.activityInfo

            if (ai.packageName.contains("com.tencent.mm")){
                break
            }
            val target = shareIntent(ShareType.IMAGE.string)
            target.putExtra(Intent.EXTRA_STREAM,uri)
            target.component = ComponentName(ai.packageName,ai.name)
            Log.d(Tag,"icon:$icon $label ${ ai.packageName}")
            targetIntents.add(LabeledIntent(target,ai.packageName,label,ai.icon))

        }

        if (targetIntents.size <= 0){
            Log.d(Tag,noAppDesc)
            return
        }
    }


    /**
     * show
     */
    fun show (){
        activity?.startActivity(Intent.createChooser(mi,title))
    }
}