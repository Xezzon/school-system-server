CREATE TABLE public.dict (
	tag varchar NOT NULL,
	code smallint NOT NULL,
	content varchar NOT NULL
);
COMMENT ON TABLE public.dict IS '字典';

-- Column comments

COMMENT ON COLUMN public.dict.tag IS '字典表';
COMMENT ON COLUMN public.dict.code IS '字典值';
COMMENT ON COLUMN public.dict.content IS '字典内容';
