package com.example.whatamieating.model.mapper

import com.example.whatamieating.model.domain.*
import com.example.whatamieating.model.remote.GetRecipeInformationResponse

typealias RemoteStep = com.example.whatamieating.model.remote.Step
typealias RemoteIngredient = com.example.whatamieating.model.remote.Ingredient
typealias RemoteNutrient = com.example.whatamieating.model.remote.Nutrient
typealias RemoteProperty = com.example.whatamieating.model.remote.Property
typealias RemoteFlavanoid = com.example.whatamieating.model.remote.Flavanoid
typealias RemoteCaloricBreakdown = com.example.whatamieating.model.remote.CaloricBreakdown

class RecipeInformationRemoteToDomainMapper :
    Mapper<GetRecipeInformationResponse, RecipeInformation> {
    override fun map(input: GetRecipeInformationResponse): RecipeInformation {

        val steps = input.analyzedInstructions?.get(0)?.steps?.map { stepRemoteToDomainMap(it) }
            ?: emptyList()

        val ingredients =
            input.extendedIngredients?.map { ingredientRemoteToDomainMap(it) } ?: emptyList()

        val nutrientList =
            input.nutrition?.nutrients?.map { nutrientRemoteToDomainMap(it) } ?: emptyList()

        val properties =
            input.nutrition?.properties?.map { propertyRemoteToDomainMap(it) } ?: emptyList()

        val flavanoids =
            input.nutrition?.flavanoids?.map { flavanoidRemoteToDomainMap(it) } ?: emptyList()

        val caloricBreakdown =
            caloricBreakdownRemoteToDomainMap(
                input.nutrition?.caloricBreakdown ?: RemoteCaloricBreakdown(null, null, null)
            )

        return RecipeInformation(
            input.aggregateLikes ?: -1,
            input.image ?: "",
            input.readyInMinutes ?: -1,
            input.title ?: "",
            input.id ?: -1,
            input.creditText ?: "Anonymous",
            input.instructions ?: "",
            steps,
            nutrientList,
            ingredients,
            properties,
            flavanoids,
            caloricBreakdown,
            input.glutenFree ?: false,
            input.dairyFree ?: false,
            input.vegetarian ?: false
        )
    }

    private fun stepRemoteToDomainMap(input: RemoteStep): Step {
        return Step(
            input.number ?: -1,
            input.step ?: "Not Available"
        )
    }

    private fun ingredientRemoteToDomainMap(input: RemoteIngredient): Ingredient {
        return Ingredient(
            input.id ?: -1,
            input.image ?: "",
            input.name ?: "Not Available",
            input.original ?: "Not Available"
        )
    }

    private fun nutrientRemoteToDomainMap(input: RemoteNutrient): Nutrient {
        return Nutrient(
            input.title ?: "Not Available",
            input.amount ?: 0.0,
            input.unit ?: "",
            input.percentOfDailyNeeds ?: 0.0
        )
    }

    private fun propertyRemoteToDomainMap(input: RemoteProperty): Property {
        return Property(
            input.amount ?: -1.0,
            input.title ?: "Not Available",
            input.unit ?: ""
        )
    }

    private fun flavanoidRemoteToDomainMap(input: RemoteFlavanoid): Flavanoid {
        return Flavanoid(
            input.amount ?: -1.0,
            input.title ?: "Not Available",
            input.unit ?: ""
        )
    }

    private fun caloricBreakdownRemoteToDomainMap(input: RemoteCaloricBreakdown): CaloricBreakdown {
        return CaloricBreakdown(
            input.percentProtein ?: -1.0,
            input.percentFat ?: -1.0,
            input.percentCarbs ?: -1.0
        )
    }
}