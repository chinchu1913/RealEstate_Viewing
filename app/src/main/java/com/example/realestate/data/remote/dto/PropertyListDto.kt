package com.example.realestate.data.remote.dto

import com.example.realestate.domain.entities.PropertyList

data class PropertyListDto(
    val items: List<SpecificationDto>,
    val totalCount: Int
)



fun PropertyListDto.toPropertyList(): PropertyList {
    return PropertyList(
        items = items.map { it.toSpecification() },
        totalCount = totalCount,
    )
}