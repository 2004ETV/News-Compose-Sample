package com.news.compose.news_api.data.headlines.remote.models

import com.news.compose.core.entity.ErrorReason

class NewsLoadException(val reason: ErrorReason): Exception()
