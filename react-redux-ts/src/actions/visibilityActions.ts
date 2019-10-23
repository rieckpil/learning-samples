export const SET_VISIBILITY_FILTER = 'SET_VISIBILITY_FILTER';

export enum VisibilityFilters {
    SHOW_ALL,
    SHOW_COMPLETED,
    SHOW_ACTIVE
}

export interface SetVisibilityFilterAction {
    type: typeof SET_VISIBILITY_FILTER,
    filter: VisibilityFilters
}

export function setVisibilityFilter(filter: VisibilityFilters): SetVisibilityFilterAction {
    return {type: SET_VISIBILITY_FILTER, filter}
}
