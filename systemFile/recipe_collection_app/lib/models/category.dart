// 🔁 Nếu chuyển sang Recipe App: đổi tên class thành RecipeCollection, fields tương ứng
class BudgetCategory {
  String name;
  double allocated;
  double spent;

  BudgetCategory({
    required this.name,
    required this.allocated,
    this.spent = 0.0,
  });
}
