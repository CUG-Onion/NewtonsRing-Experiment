@echo on
del *.pdf


::�������ļ����ļ���
set  Name=NetonRing
set	 FinalName=NetonRing

lualatex "%Name%.tex"
lualatex "%Name%.tex"

start	%FinalName%.pdf
