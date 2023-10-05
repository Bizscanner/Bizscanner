import React, { forwardRef, useEffect, useRef, useState } from 'react';
import ReportSection from './ReportSection';
import SendIcon from '@/assets/icons/Send.svg';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import CloseIcon from '@/assets/icons/close.svg';
import { useSelector } from 'react-redux';

function ReportCommentItem({ nickName, contents, deleteComment, commentId }) {
  const { nickname: myNickName } = useSelector((state) => state.user);

  return (
    <div className="py-4 border-b border-black">
      <div className="flex items-center justify-between text-xl font-bold">
        <div className="grow">{nickName}</div>{' '}
        {nickName === myNickName && (
          <button className="mr-2" onClick={() => deleteComment(commentId)}>
            <CloseIcon height="20" width="20" />
          </button>
        )}
      </div>
      <div>{contents}</div>
    </div>
  );
}

const CommentInput = forwardRef(function CommentInput(
  { placeholder, registerComment, disabled },
  ref,
) {
  const inputRef = useRef(null);

  const onInputEnter = (e) => {
    if (e.keyCode !== 13) {
      return;
    }

    registerComment(inputRef.current.value);
    inputRef.current.value = '';
  };

  const onClickButton = (e) => {
    registerComment(inputRef.current.value);
    inputRef.current.value = '';
  };

  return (
    <div className="flex items-center h-12 px-4 overflow-hidden border rounded-full border-outline">
      <input
        placeholder={placeholder}
        className="h-full grow outline-0"
        ref={inputRef}
        onKeyUp={onInputEnter}
        disabled={disabled}
      />
      <button
        className="relative flex items-center w-8 h-8 p-1 ml-1 rounded-full bg-primary"
        onClick={onClickButton}
        disabled={disabled}
      >
        <SendIcon width="22" className="absolute right-[6px]" />
      </button>
    </div>
  );
});

function ReportComment({ careaCode, jcategoryCode }) {
  const [comments, setComments] = useState([]);
  const { isLogin } = useSelector((state) => state.user);
  const commentsContainerRef = useRef(null);

  const fetchComments = async () => {
    const {
      data: { commentResponseList },
    } = await axios.get(`/comment/${careaCode}`);

    setComments(commentResponseList);
  };

  useEffect(() => {
    fetchComments();
  }, []);

  const deleteComment = async (commentId) => {
    await axios.delete(`/comment/${commentId}`);

    await fetchComments();
  };

  const registerComment = async (contents) => {
    await axios.post(`/comment`, {
      careaCode,
      jcategoryCode,
      contents,
    });

    fetchComments();
  };

  return (
    <ReportSection title="코멘트" className="h-full">
      <div className="overflow-auto h-[80%] scrollbar-hide mb-4" ref={commentsContainerRef}>
        {comments &&
          comments.map(({ nickname, contents, commentId }) => (
            <ReportCommentItem
              nickName={nickname}
              contents={contents}
              commentId={commentId}
              deleteComment={deleteComment}
            />
          ))}
      </div>
      <CommentInput
        placeholder={isLogin ? '댓글 추가' : '로그인이 필요해요'}
        disabled={!isLogin}
        registerComment={registerComment}
      />
    </ReportSection>
  );
}

export default ReportComment;
