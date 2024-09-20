CREATE TABLE IF NOT EXISTS T_PD_LIVROS(
    id_livro bigint auto_increment,
    nm_livro varchar(255),
    ds_livro varchar(255),
    tp_livro varchar(255),
    privary key(id_livro)
);