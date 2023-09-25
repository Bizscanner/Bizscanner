'use client';

const { createContext, useContext, useReducer } = require('react');

const SearchStateContext = createContext(null);
const SearchDispatchContext = createContext(null);

const initialState = {
  dongCode: 11110515,
  dongName: '청운효자동',
  sigunguCode: 11110,
  sigunguName: '종로구',
};

function reducer(state, action) {
    
  switch (action.type) {
    case 'SET_SIGUNGU':
      console.log(action);
      return {
        ...state,
        sigunguCode: action.sigunguCode,
        sigunguName: action.sigunguName,
        dongCode: action.sigunguName,
        dongName: action.dongName,
      };

    case 'SET_DONG':
      return {
        ...state,
        dongCode: action.dongCode,
        dongName: action.dongName,
      };

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
