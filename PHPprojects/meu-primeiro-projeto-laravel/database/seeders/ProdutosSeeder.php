<?php

namespace Database\Seeders;

use App\Models\Produto;
use Illuminate\Database\Seeder;
use Faker\Factory as Faker;

class ProdutosSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run()
    {
        $faker = Faker::create();


        // Gerar 50 produtos
        for ($i = 1; $i <= 50; $i++) {
            Produto::create([
                'nome' => 'Produto ' . $i,
                'descricao' => $faker->sentence,
                'preco' => $faker->randomFloat(2, 1, 1000),
                'quantidade' => $faker->numberBetween(1, 100),
            ]);
        }
    }

}
