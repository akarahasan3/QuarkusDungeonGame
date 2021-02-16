import 'package:flutter/foundation.dart';

import 'dungeon.dart';
import 'monster.dart';
import 'player.dart';

class Weapon {
  final int id;
  final String name;
  final int damage;
  final List<Player> players;
  final List<Monster> monsters;
  final Dungeon dungeon;

  Weapon({
    @required this.id,
    @required this.name,
    @required this.damage,
    @required this.players,
    @required this.monsters,
    @required this.dungeon,
  });
}

// class Weapons with ChangeNotifier {
//   List<Weapon> _weapons = [];
//   List<Weapon> get weapons {
//     return [..._weapons];
//   }
// }
