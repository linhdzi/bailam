import 'package:flutter/material.dart';
import '../models/category.dart';

class AddCategoryScreen extends StatefulWidget {
  final bool isEditing;
  final BudgetCategory? category;

  const AddCategoryScreen({
    super.key,
    this.isEditing = false,
    this.category,
  });

  @override
  State<AddCategoryScreen> createState() => _AddCategoryScreenState();
}

class _AddCategoryScreenState extends State<AddCategoryScreen> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _allocatedController = TextEditingController();

  @override
  void initState() {
    super.initState();
    if (widget.isEditing && widget.category != null) {
      _nameController.text = widget.category!.name;
      _allocatedController.text = widget.category!.allocated.toString();
    }
  }

  void save() {
    if (_formKey.currentState!.validate()) {
      final newCategory = BudgetCategory(
        name: _nameController.text.trim(),
        allocated: double.parse(_allocatedController.text),
        spent: widget.category?.spent ?? 0,
      );
      Navigator.pop(context, newCategory);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.isEditing ? 'Edit Category' : 'Add Category')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _nameController,
                decoration: const InputDecoration(labelText: 'Category Name'),
                validator: (val) =>
                    val == null || val.isEmpty ? 'Enter a name' : null,
              ),
              TextFormField(
                controller: _allocatedController,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(labelText: 'Allocated Amount'),
                validator: (val) {
                  final amount = double.tryParse(val ?? '');
                  if (amount == null || amount <= 0) {
                    return 'Enter a valid amount';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),
              ElevatedButton(onPressed: save, child: const Text('Save')),
            ],
          ),
        ),
      ),
    );
  }
}
