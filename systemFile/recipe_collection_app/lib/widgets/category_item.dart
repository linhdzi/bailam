import 'package:flutter/material.dart';
import '../models/category.dart';

class CategoryItem extends StatelessWidget {
  final BudgetCategory category;
  final VoidCallback onEdit;
  final VoidCallback onDelete;
  final VoidCallback onAddTransaction;

  const CategoryItem({
    super.key,
    required this.category,
    required this.onEdit,
    required this.onDelete,
    required this.onAddTransaction,
  });

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(category.name),
      subtitle: Text(
          'Allocated: \$${category.allocated}, Spent: \$${category.spent}'),
      trailing: Wrap(
        spacing: 8,
        children: [
          IconButton(onPressed: onAddTransaction, icon: const Icon(Icons.add)),
          IconButton(onPressed: onEdit, icon: const Icon(Icons.edit)),
          IconButton(
            icon: const Icon(Icons.delete),
            onPressed: () {
              showDialog(
                context: context,
                builder: (_) => AlertDialog(
                  title: const Text('Delete'),
                  content: const Text('Are you sure?'),
                  actions: [
                    TextButton(
                      child: const Text('Cancel'),
                      onPressed: () => Navigator.pop(context),
                    ),
                    TextButton(
                      child: const Text('Delete'),
                      onPressed: () {
                        Navigator.pop(context);
                        onDelete();
                      },
                    ),
                  ],
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
