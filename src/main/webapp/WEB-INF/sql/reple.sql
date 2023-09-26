-- 시퀀스 리셋
-- 데이터가 있다면 데이터 삭제후 시퀀스 생성

--    delete from reple;
    drop sequence reple_idx_seq;
    create sequence reple_idx_seq;

select * from reple;

--select * from reple where id = ???;

commit;