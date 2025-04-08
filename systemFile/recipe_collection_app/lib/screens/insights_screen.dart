import 'package:flutter/material.dart';
import '../models/category.dart';

class InsightsScreen extends StatelessWidget {
  final List<BudgetCategory> categories;

  const InsightsScreen({super.key, required this.categories});

  @override
  Widget build(BuildContext context) {
    final totalBudget =
        categories.fold(0.0, (sum, cat) => sum + cat.allocated);
    final totalSpent =
        categories.fold(0.0, (sum, cat) => sum + cat.spent);
    final topCategory = categories.reduce(
        (curr, next) => curr.spent > next.spent ? curr : next);

    return Scaffold(
      appBar: AppBar(title: const Text('Spending Insights')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Total Budget: \$${totalBudget.toStringAsFixed(2)}'),
            Text('Total Spent: \$${totalSpent.toStringAsFixed(2)}'),
            Text('Top Category: ${topCategory.name}'),
            const Divider(),
            ...categories.map((cat) => Text(
                '${cat.name}: Remaining \$${(cat.allocated - cat.spent).toStringAsFixed(2)}')),
          ],
        ),
      ),
    );
  }
}
