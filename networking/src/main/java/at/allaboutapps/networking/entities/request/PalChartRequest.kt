package com.palfinger.palfingerapi.entities.request


data class PalChartRequest(var equipmentnumber: Int,
                           var lca03: Boolean = false,
                           var lca05: Boolean = false,
                           var weight: Int = 0,
                           var degree: Int = 0,
                           var supportPoints: List<SupportRequestPoint>)