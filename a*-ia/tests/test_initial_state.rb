require_relative '../codes/initial_state'
require 'test/unit'

#
class TestInitialState < Test::Unit::TestCase
  def test_returned_shuffled_array
    assert_not_equal([0, 1, 2, 3, 4, 5, 6, 7, 8],
                     initial_state)
  end

  def test_default_arry_size_nine
    assert_equal(9, initial_state.size)
  end

  def test_truncated_board_size
    assert_equal(11, initial_state(10.5).size)
  end

  def test_never_empty_return
    assert_not_equal([], initial_state)
  end
end
