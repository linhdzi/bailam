import 'package:flutter/material.dart';

class AddTransactionScreen extends StatelessWidget {
  const AddTransactionScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final category = ModalRoute.of(context)!.settings.arguments;

    final TextEditingController descController = TextEditingController();
    final TextEditingController amountController = TextEditingController();

    return Scaffold(
      appBar: AppBar(title: const Text('Add Transaction')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            TextField(
              controller: descController,
              decoration: const InputDecoration(labelText: 'Description'),
            ),
            TextField(
              controller: amountController,
              decoration: const InputDecoration(labelText: 'Amount'),
              keyboardType: TextInputType.number,
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                final amount = double.tryParse(amountController.text);
                if (descController.text.isNotEmpty &&
                    amount != null &&
                    amount > 0) {
                  Navigator.pop(context, amount);
                }
              },
              child: const Text('Add Transaction'),
            ),
          ],
        ),
      ),
    );
  }
}
