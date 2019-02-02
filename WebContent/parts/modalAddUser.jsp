<!-- Modal -->
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ModalAddtableTitle">Crea nuovo tavolo</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form>
       		<div class="form-group">
       			<label for="numeroTavolo">Numero Tavolo</label>
    			<input type="number" class="form-control" id="numeroTavolo" placeholder="N°" min="0">	
       		</div>
       </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id)">Crea</button>
      </div>
    </div>
  </div>
  
  <script src="./contents/scripts/modalFormTable.js"></script>