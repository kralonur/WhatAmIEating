package com.example.whatamieating.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}