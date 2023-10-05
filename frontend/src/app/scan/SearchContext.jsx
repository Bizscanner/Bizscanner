'use client';

const { createContext, useContext, useReducer } = require('react');

const SearchStateContext = createContext(null);
const SearchDispatchContext = createContext(null);

const initialState = {
  dongCode: 0,
  dongName: '',
  sigunguCode: 11110,
  sigunguName: '종로구',
  careaCode: '',
  careaName: '',
  bizCode: '',
  bizName: '',
  jcategoryCode: '',
  jcategoryName: '',
  mapCenter: null,
  mapCoordinates: null,
};

function reducer(state, action) {
  switch (action.type) {
    case 'SET_SIGUNGU':
      return {
        ...state,
        sigunguCode: action.sigunguCode,
        sigunguName: action.sigunguName,
        dongCode: action.dongCode,
        dongName: action.dongName,
        mapCenter: action.mapCenter,
        mapCoordinates: action.mapCoordinates,
        mapZoom: action.mapZoom,
      };

    case 'SET_DONG':
      return {
        ...state,
        dongCode: action.dongCode,
        dongName: action.dongName,
        mapCenter: action.mapCenter,
        mapCoordinates: action.mapCoordinates,
        mapZoom: action.mapZoom,
      };

    case 'SET_CAREA':
      return {
        ...state,
        careaCode: action.careaCode,
        careaName: action.careaName,
        mapCenter: action.mapCenter,
        mapCoordinates: action.mapCoordinates || state.mapCoordinates,
        mapZoom: action.mapZoom || state.mapZoom,
      };

    case 'INIT_PLACE':
      return {
        ...state,
        mapCoordinates: action.mapCoordinates,
        mapZoom: action.mapZoom,
        mapCenter: action.mapCenter ?? state.mapCenter,
        dongName: '',
        dongCode: 0,
      };

    case 'SET_CAREA_MAP':
      return {
        ...state,
        mapCoordinates: action.mapCoordinates,
        mapZoom: action.mapZoom,
      };

    case 'SET_BIZ':
      return {
        ...state,
        bizCode: action.bizCode,
        bizName: action.bizName,
      };

    case 'SET_JCATEGORY':
      return {
        ...state,
        jcategoryCode: action.jcategoryCode,
        jcategoryName: action.jcategoryName,
      };

    case 'RESET':
      return initialState;

    default:
      return state;
  }
}

export function SearchProvider({ children }) {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <SearchStateContext.Provider value={state}>
      <SearchDispatchContext.Provider value={dispatch}>{children}</SearchDispatchContext.Provider>
    </SearchStateContext.Provider>
  );
}

export function useSearchState() {
  const state = useContext(SearchStateContext);
  return state;
}

export function useSearchDispatch() {
  const dispatch = useContext(SearchDispatchContext);
  return dispatch;
}
