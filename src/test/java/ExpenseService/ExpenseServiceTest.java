package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.ExpenseService.getExpenseCodeByProjectTypeAndName;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL,"xxx");
        // when
        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,result);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(ExpenseType.EXPENSE_TYPE_A,result);

    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project B");
        // when
        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(ExpenseType.EXPENSE_TYPE_B,result);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL,"Project AB");
        // when
        ExpenseType result = getExpenseCodeByProjectTypeAndName(project);
        // then
        assertEquals(ExpenseType.OTHER_EXPENSE,result);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"name");
        // when
        Throwable exception1= Assertions.assertThrows(UnexpectedProjectTypeException.class,() -> getExpenseCodeByProjectTypeAndName(project));
        // then
        assertEquals("You enter invalid project type",exception1.getMessage());
    }
}