package dev.feryadi.bayu.resourcestate

data class ResourceState<S, E>(
    val state: State,
    val data: S?,
    val error: E?
) {
    companion object {
        fun <S, E> success(data: S): ResourceState<S, E> {
            return ResourceState(state = State.SUCCESS, data = data, error = null)
        }

        fun <S, E> loading(): ResourceState<S, E> {
            return ResourceState(state = State.LOADING, data = null, error = null)
        }

        fun <S, E> error(error: E): ResourceState<S, E> {
            return ResourceState(state = State.ERROR, data = null, error = error)
        }
    }
}