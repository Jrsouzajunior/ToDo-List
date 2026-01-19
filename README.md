# 🚀 TODO List API

API robusta desenvolvida em **Java Spring Boot** para gerenciamento inteligente de tarefas, focada em produtividade e automação.

## 🌟 Funcionalidades de Destaque (Desafios Extras)

Este projeto foi além do básico, implementando regras de negócio:

* **🔄 Tarefas Recorrentes**: Sistema automático que gera novas instâncias de tarefas baseadas em frequência (Diária, Semanal, Mensal) ao concluir a tarefa atual.
* **🔔 Lembretes Automáticos**: Verificação agendada via `@Scheduled` que monitora prazos e alerta sobre tarefas próximas ao vencimento.
* **💬 Histórico de Comentários**: Possibilidade de adicionar notas e observações em cada tarefa para rastreabilidade.
* **📋 Subtarefas (Checklist)**: Gerenciamento de sub-itens para decompor tarefas grandes em passos menores.
* **👥 Gestão de Usuários**: Atribuição de tarefas a usuários específicos com validações de segurança e integridade.

## ⚙️ Lógica de Automação
Tarefas Recorrentes: Ao marcar uma tarefa como CONCLUIDA, o sistema verifica o campo recorrencia. Se for DIARIA, SEMANAL ou MENSAL, uma nova tarefa é instanciada automaticamente com a data de vencimento ajustada e status PENDENTE.

Agendamento (Scheduling): O sistema executa uma rotina em segundo plano (via @Scheduled) que varre o banco de dados em busca de tarefas pendentes que vencem nas próximas 24 horas, emitindo alertas preventivos.

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3**
* **Spring Data JPA** (Persistência e Query Methods)
* **PostgreSQL/H2 Database**
* **Hibernate** (Mapeamento Objeto-Relacional)
* **Lombok** & **Jakarta Validation**

## 🚀 Como Executar

1. Clone o repositório.
2. Certifique-se de ter o Maven instalado.
3. Execute o comando `mvn spring-boot:run`.
4. Acesse via Postman na porta `8081`.

## 📂 Exemplo de Criação de Tarefa com Recorrência
{
    "titulo": "Estudar Spring Security",
    "recorrencia": "SEMANAL",
    "prioridade": 1,
    "dataVencimento": "2026-01-26T09:00:00"
}
