package com.palfinger.palfingerapi.entities

data class EquipmentStatus(val operatingHours: List<OperatingHour> = listOf(),
                           val servicePartner: ServicePartner = ServicePartner(),
                           val equipmentData: Map<String,String> =  mapOf())