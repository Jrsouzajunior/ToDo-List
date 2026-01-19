package com.todo.list.todo_list.model;

public enum StatusTarefa {
        PENDENTE("Pendente"),
        EM_PROGRESSO("Em Progresso"),
        CONCLUIDA("Concluída"),
        CANCELADA("Cancelada");

        private String descricao;

        StatusTarefa(String descricao) {
                this.descricao = descricao;
        }

        public String getDescricao() {
                return descricao;
        }
}
