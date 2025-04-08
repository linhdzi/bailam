import 'package:flutter/material.dart';
import '../models/category.dart';
import '../widgets/category_item.dart';
import 'add_category_screen.dart';

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({super.key});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  List<BudgetCategory> categories = [
    BudgetCategory(name: 'Food', allocated: 1000, spent: 800),
    BudgetCategory(name: 'Transport', allocated: 500, spent: 300),
    BudgetCategory(name: 'Entertainment', allocated: 1500, spent: 1000),
  ];

  double get totalBudget =>
      categories.fold(0, (sum, cat) => sum + cat.allocated);

  void addCategory(BudgetCategory category) {
    setState(() => categories.add(category));
  }

  void updateCategory(int index, BudgetCategory updated) {
    setState(() => categories[index] = updated);
  }

  void deleteCategory(int index) {
    setState(() => categories.removeAt(index));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Dashboard'),
      ),
      body: Column(
        children: [
          Container(
            padding: const EdgeInsets.all(16),
            child: Text('Total Budget: \$${totalBudget.toStringAsFixed(2)}',
                style: const TextStyle(fontSize: 20)),
          ),
          const Divider(),
          Expanded(
            child: ListView.builder(
              itemCount: categories.length,
              itemBuilder: (ctx, index) => CategoryItem(
                category: categories[index],
                onEdit: () async {
                  final updated = await Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (_) => AddCategoryScreen(
                        isEditing: true,
                        category: categories[index],
                      ),
                    ),
                  );
                  if (updated != null) updateCategory(index, updated);
                },
                onDelete: () => deleteCategory(index),
                onAddTransaction: () async {
                  final amount = await Navigator.pushNamed(
                    context,
                    '/addTransaction',
                    arguments: categories[index],
                  ) as double?;
                  if (amount != null) {
                    setState(() => categories[index].spent += amount);
                  }
                },
              ),
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () async {
          final newCategory = await Navigator.push(
            context,
            MaterialPageRoute(builder: (_) => const AddCategoryScreen()),
          );
          if (newCategory != null) addCategory(newCategory);
        },
      ),
    );
  }
}
