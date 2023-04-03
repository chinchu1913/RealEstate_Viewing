package com.example.realestate.domain.entities


data class PropertyList(
    val items: List<Specification>,
    val totalCount: Int
)